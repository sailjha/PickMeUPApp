package com.codeshail.pickmeupapp.data.repositories

import android.app.Application
import com.codeshail.pickmeupapp.data.datasources.local.LocalDataSource
import com.codeshail.pickmeupapp.data.datasources.local.entity.WatchListEntity
import com.codeshail.pickmeupapp.data.datasources.remote.RemoteDataSource
import com.codeshail.pickmeupapp.domain.models.WatchProviders
import com.codeshail.pickmeupapp.domain.repositories.WatchListInfoRepository
import com.codeshail.pickmeupapp.utils.NetworkResult
import com.codeshail.pickmeupapp.utils.isNetworkAvailable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WatchListInfoRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val appContext: Application
) : WatchListInfoRepository {

    // This function returns a Flow that emits the list of fetching watchlist info data.
    override fun getWatchListInfo(): Flow<List<WatchListEntity>> = flow {
        emit(local.readWatchListData())
    }

    // This function insert watchlist info in roomDb.
    override suspend fun insertWatchListInfo(watchListEntity: WatchListEntity) {
        local.insertWatchListData(watchListEntity)
    }

    // This function delete watchlist info in roomDb.
    override suspend fun deleteWatchListInfo(watchListEntity: WatchListEntity) {
        local.deleteWatchListData(watchListEntity)
    }

    // This function returns a Flow that emits the network result of watchlist provider data.
    override fun getMovieWatchProviders(movieId: Int): Flow<NetworkResult<WatchProviders>> =
        flow {
            emit(NetworkResult.Loading())

            try {
                if (isNetworkAvailable(appContext)) {
                    val apiResponse = remote.fetchMovieWatchProviders(movieId)
                    // Emit the success state with the fetched data
                    emit(NetworkResult.Success(apiResponse.body()?.toWatchProviders()))
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        emit(
                            NetworkResult.Error(
                                throwable.message ?: "Something went wrong",
                            )
                        )
                    }

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