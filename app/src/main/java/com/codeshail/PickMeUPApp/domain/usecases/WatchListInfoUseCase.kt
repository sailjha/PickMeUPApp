package com.codeshail.pickmeupapp.domain.usecases

import com.codeshail.pickmeupapp.data.datasources.local.entity.WatchListEntity
import com.codeshail.pickmeupapp.domain.models.WatchProviders
import com.codeshail.pickmeupapp.domain.repositories.WatchListInfoRepository
import com.codeshail.pickmeupapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class WatchListInfoUseCase(
    private val repository: WatchListInfoRepository
) {
    fun getWatchListInfo(): Flow<List<WatchListEntity>> {
        return repository.getWatchListInfo()
    }

    suspend fun insertWatchListInfo(watchListEntity: WatchListEntity) {
        repository.insertWatchListInfo(watchListEntity)
    }

    suspend fun deleteWatchListInfo(watchListEntity: WatchListEntity) {
        repository.deleteWatchListInfo(watchListEntity)
    }

    fun getMovieWatchProviders(movieId: Int): Flow<NetworkResult<WatchProviders>> {
        return repository.getMovieWatchProviders(movieId)
    }
}