package com.codeshail.pickmeupapp.data.repositories

import android.app.Application
import com.codeshail.pickmeupapp.data.datasources.remote.RemoteDataSource
import com.codeshail.pickmeupapp.domain.models.MovieList
import com.codeshail.pickmeupapp.domain.repositories.SearchInfoRepository
import com.codeshail.pickmeupapp.utils.NetworkResult
import com.codeshail.pickmeupapp.utils.isNetworkAvailable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class SearchInfoRepositoryImpl(
    private val remote: RemoteDataSource,
    private val appContext: Application
) : SearchInfoRepository {

    // This function returns a Flow that emits the network result of fetching searched movie data.
    override fun searchMovie(searchQuery: String): Flow<NetworkResult<MovieList>> = flow {
        emit(NetworkResult.Loading())
        try {
            if (isNetworkAvailable(appContext)) {
                val apiResponse = remote.fetchMovieSearchedResults(searchQuery)

                // Emit the success state with the fetched data
                emit(NetworkResult.Success(apiResponse.body()?.toMovieList()))
            }
        } catch (throwable: Throwable) {
            when (throwable) {

                is IOException -> {
                    emit(
                        NetworkResult.Error(
                            "Please check your internet connection"
                        )
                    )
                }

                else -> {
                    emit(
                        NetworkResult.Error(
                            throwable.message ?: "Something went wrong"
                        )
                    )
                }
            }
        }
    }

    // This function returns a Flow that emits the network result of fetching trending movies data.
    override fun trendingMovies(): Flow<NetworkResult<MovieList>> = flow {
        emit(NetworkResult.Loading())
        try {
            if (isNetworkAvailable(appContext)) {
                val apiResponse = remote.fetchTrending()
                // Emit the success state with the fetched data
                emit(NetworkResult.Success(apiResponse.body()?.toMovieList()))
            }
        } catch (throwable: Throwable) {
            when (throwable) {

                is IOException -> {
                    emit(
                        NetworkResult.Error(
                            "Please check your internet connection"
                        )
                    )
                }

                else -> {
                    emit(
                        NetworkResult.Error(
                            throwable.message ?: "Something went wrong"
                        )
                    )
                }
            }
        }
    }
}