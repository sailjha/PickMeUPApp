package com.codeshail.pickmeupapp.domain.usecases

import com.codeshail.pickmeupapp.domain.models.HomeFeedData
import com.codeshail.pickmeupapp.domain.models.MovieList
import com.codeshail.pickmeupapp.domain.models.MovieVideoList
import com.codeshail.pickmeupapp.domain.repositories.MovieInfoRepository
import com.codeshail.pickmeupapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetMovieInfoUseCase(private val repository: MovieInfoRepository) {

    fun getHomeFeedData(): Flow<NetworkResult<HomeFeedData>> {
        return repository.getHomeFeedData()
    }

    fun getMovieRecommendationsData(movieId: Int): Flow<NetworkResult<MovieList>> {
        return repository.getMovieRecommendationsData(movieId)
    }

    fun getMovieTrailerData(movieId: Int): Flow<NetworkResult<MovieVideoList>> {
        return repository.getMovieTrailerData(movieId)
    }
}
