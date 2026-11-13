package com.codeshail.pickmeupapp.domain.repositories

import com.codeshail.pickmeupapp.domain.models.HomeFeedData
import com.codeshail.pickmeupapp.domain.models.MovieList
import com.codeshail.pickmeupapp.domain.models.MovieVideoList
import com.codeshail.pickmeupapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieInfoRepository {

    fun getHomeFeedData(): Flow<NetworkResult<HomeFeedData>>
    fun getMovieRecommendationsData(movieId:Int): Flow<NetworkResult<MovieList>>
    fun getMovieTrailerData(movieId:Int): Flow<NetworkResult<MovieVideoList>>

}