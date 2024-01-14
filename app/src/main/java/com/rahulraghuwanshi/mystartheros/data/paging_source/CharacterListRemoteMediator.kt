package com.rahulraghuwanshi.mystartheros.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rahulraghuwanshi.mystartheros.data.local.StarWarDatabase
import com.rahulraghuwanshi.mystartheros.domain.model.Character
import com.rahulraghuwanshi.mystartheros.domain.model.RemoteKeys
import com.rahulraghuwanshi.mystartheros.domain.use_case.FetchCharactersListUseCase
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * RemoteMediator acts as a signal from the Paging library when the app has run out of cached data.
 * You can use this signal to load additional data from the network and store it in the local database,
 * where a PagingSource can load it and provide it to the UI to display.
 */
@OptIn(ExperimentalPagingApi::class)
class CharacterListRemoteMediator(
    private val fetchCharactersListUseCase: FetchCharactersListUseCase,
    private val starWarDatabase: StarWarDatabase
) : RemoteMediator<Int, Character>() {

    /**
     * When additional data is needed, the Paging library calls the load() method from the RemoteMediator implementation.
     * This function typically fetches the new data from a network source and saves it to local storage.
     * Over time the data stored in the database requires invalidation, such as when the user manually triggers a refresh.
     * This is represented by the LoadType property passed to the load() method.
     * The LoadType informs the RemoteMediator whether it needs to refresh the existing data or fetch additional data that needs to be appended or prepended to the existing list.


     * In cases where the local data needs to be fully refreshed, initialize() should return InitializeAction.LAUNCH_INITIAL_REFRESH.
     * This causes the RemoteMediator to perform a remote refresh to fully reload the data.
     *
     * In cases where the local data doesn't need to be refreshed, initialize() should return InitializeAction.SKIP_INITIAL_REFRESH.
     * This causes the RemoteMediator to skip the remote refresh and load the cached data.
     */
    override suspend fun initialize(): InitializeAction {
        Log.d("MAJAMA", "initialize() called")
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return if (System.currentTimeMillis() - (starWarDatabase.remoteKeyDao().getCreationTime()
                ?: 0) < cacheTimeout
        ) {
            // Cached data is up-to-date, so there is no need to re-fetch
            // from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    /** LoadType.Append
     * When we need to load data at the end of the currently loaded data set, the load parameter is LoadType.APPEND
     */
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Character>): RemoteKeys? {
        Log.d("MAJAMA", "getRemoteKeyForLastItem() called with: state = $state")
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { character ->
            starWarDatabase.remoteKeyDao().getRemoteKeyByCharacterID("${character.birthYear}_${character.eyeColor}_${character.gender}_${character.hairColor}_${character.height}_${character.name}")
        }
    }

    /** LoadType.Prepend
     * When we need to load data at the beginning of the currently loaded data set, the load parameter is LoadType.PREPEND
     */
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Character>): RemoteKeys? {
        Log.d("MAJAMA", "getRemoteKeyForFirstItem() called with: state = $state")
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { character ->
            starWarDatabase.remoteKeyDao().getRemoteKeyByCharacterID("${character.birthYear}_${character.eyeColor}_${character.gender}_${character.hairColor}_${character.height}_${character.name}")
        }
    }

    /** LoadType.REFRESH
     * Gets called when it's the first time we're loading data, or when PagingDataAdapter.refresh() is called;
     * so now the point of reference for loading our data is the state.anchorPosition.
     * If this is the first load, then the anchorPosition is null.
     * When PagingDataAdapter.refresh() is called, the anchorPosition is the first visible position in the displayed list, so we will need to load the page that contains that specific item.
     */
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Character>): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { character ->
                starWarDatabase.remoteKeyDao().getRemoteKeyByCharacterID("${character.birthYear}_${character.eyeColor}_${character.gender}_${character.hairColor}_${character.height}_${character.name}")
            }
        }
    }

    /**.
     *
     * @param state This gives us information about the pages that were loaded before,
     * the most recently accessed index in the list, and the PagingConfig we defined when initializing the paging stream.
     * @param loadType this tells us whether we need to load data at the end (LoadType.APPEND)
     * or at the beginning of the data (LoadType.PREPEND) that we previously loaded,
     * or if this the first time we're loading data (LoadType.REFRESH).
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                //New Query so clear the DB
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                val prevKey = remoteKeys?.prevKey
                prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)

                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with endOfPaginationReached = false because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                // the end of pagination for append.
                val nextKey = remoteKeys?.nextKey
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = fetchCharactersListUseCase.fetchCharacters(page = page)

            val characterDetailsList = apiResponse.data?.characterList ?: emptyList()
            val endOfPaginationReached = characterDetailsList.isEmpty()

            starWarDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    starWarDatabase.remoteKeyDao().clearRemoteKeys()
                    starWarDatabase.starWarDao().clearAllCharacters()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = characterDetailsList.map {
                    RemoteKeys(
                        characterListID = "${it.birthYear}_${it.eyeColor}_${it.gender}_${it.hairColor}_${it.height}_${it.name}",
                        prevKey = prevKey,
                        currentPage = page,
                        nextKey = nextKey
                    )
                }

                starWarDatabase.remoteKeyDao().insertAll(remoteKeys)
                starWarDatabase.starWarDao()
                    .insertAllCharacters(charactersDetailList = characterDetailsList)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            error.printStackTrace()
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            error.printStackTrace()
            return MediatorResult.Error(error)
        }
    }
}