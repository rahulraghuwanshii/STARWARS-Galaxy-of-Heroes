package com.rahulraghuwanshi.starwarshero.presentation.character_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulraghuwanshi.starwarshero.data.network.model.HomeWorldResponse
import com.rahulraghuwanshi.starwarshero.data.network.model.MoviesResponse
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterHomeWorldUseCase
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterMoviesUseCase
import com.rahulraghuwanshi.starwarshero.util.RestClientResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val fetchCharacterHomeWorldUseCase: FetchCharacterHomeWorldUseCase,
    private val fetchCharacterMoviesUseCase: FetchCharacterMoviesUseCase
) : ViewModel() {

    private val _homeWorldFlow =
        MutableStateFlow<RestClientResult<HomeWorldResponse>>(RestClientResult.none())
    val homeWorldFlow = _homeWorldFlow.asStateFlow()

    private val _movieDetailsFlow =
        MutableStateFlow<RestClientResult<List<MoviesResponse>>>(RestClientResult.none())
    val movieDetailsFlow = _movieDetailsFlow.asStateFlow()

    private val moviesList: ArrayList<MoviesResponse> = ArrayList()


    fun getMoviesData(filmList: List<String>) {
        filmList.forEach { movie ->
            viewModelScope.launch(Dispatchers.IO) {
                fetchCharacterMoviesUseCase.fetchMovies(movie).collect {
                    when (it.status) {
                        RestClientResult.Status.SUCCESS -> {
                            it.data?.let { film ->
                                moviesList.add(film)
                                _movieDetailsFlow.emit(RestClientResult.success(moviesList))
                            }
                        }

                        RestClientResult.Status.ERROR -> {
                            _movieDetailsFlow.emit(
                                RestClientResult.error(
                                    it.message ?: "Something went wrong!!"
                                )
                            )
                        }

                        RestClientResult.Status.LOADING -> {
                            _movieDetailsFlow.emit(RestClientResult.loading())
                        }

                        RestClientResult.Status.NONE -> {
                            _movieDetailsFlow.emit(RestClientResult.none())
                        }
                    }
                }
            }
        }
    }


    fun getHomeWorldData(homeWorldUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchCharacterHomeWorldUseCase.fetchHomeWorld(homeWorldUrl).collect {
                _homeWorldFlow.emit(it)
            }
        }
    }
}