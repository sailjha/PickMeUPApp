package com.codeshail.pickmeupapp.domain.usecases

import com.codeshail.pickmeupapp.domain.models.MovieList
import com.codeshail.pickmeupapp.domain.repositories.SearchInfoRepository
import com.codeshail.pickmeupapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class SearchListInfoUseCase(
    private val repository: SearchInfoRepository
) {

    fun searchMovieData(searchQuery: String): Flow<NetworkResult<MovieList>> {
        return repository.searchMovie(searchQuery)
    }

    fun getTrendingMovies(): Flow<NetworkResult<MovieList>> {
        return repository.trendingMovies()
    }
}