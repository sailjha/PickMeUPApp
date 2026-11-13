package com.codeshail.pickmeupapp.domain.repositories

import com.codeshail.pickmeupapp.domain.models.MovieList
import com.codeshail.pickmeupapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SearchInfoRepository {

    fun searchMovie(searchQuery:String): Flow<NetworkResult<MovieList>>
    fun trendingMovies(): Flow<NetworkResult<MovieList>>


}