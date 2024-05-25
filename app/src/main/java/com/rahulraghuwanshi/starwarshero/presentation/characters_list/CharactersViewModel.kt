package com.rahulraghuwanshi.starwarshero.presentation.characters_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rahulraghuwanshi.starwarshero.data.local.StarWarDatabase
import com.rahulraghuwanshi.starwarshero.data.paging_source.CharacterListRemoteMediator
import com.rahulraghuwanshi.starwarshero.domain.model.Character
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharactersListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersViewModel constructor(
    private val starWarDatabase: StarWarDatabase,
    private val fetchCharactersListUseCase: FetchCharactersListUseCase,
) : ViewModel() {

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    private val _characterListFlow =
        MutableStateFlow<PagingData<Character>?>(null)
    val characterListFlow: StateFlow<PagingData<Character>?>
        get() = _characterListFlow.asStateFlow()

    /**
     * A PagingSource still loads the data; but when the paged data is exhausted, the Paging library triggers the RemoteMediator to load new data from the network source.
     * The RemoteMediator stores the new data in the local database, so an in-memory cache in the ViewModel is unnecessary.
     * Finally, the PagingSource invalidates itself, and the Pager creates a new instance to load the fresh data from the database.
     */
    @OptIn(ExperimentalPagingApi::class)
    fun fetchCharactersList() {
        viewModelScope.launch(Dispatchers.IO) {
            Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    initialLoadSize = NETWORK_PAGE_SIZE
                ),
                pagingSourceFactory = { starWarDatabase.starWarDao().getAllCharacters() },
                remoteMediator = CharacterListRemoteMediator(
                    starWarDatabase = starWarDatabase,
                    fetchCharactersListUseCase = fetchCharactersListUseCase
                )
            ).flow.cachedIn(viewModelScope).collectLatest {
                _characterListFlow.emit(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}