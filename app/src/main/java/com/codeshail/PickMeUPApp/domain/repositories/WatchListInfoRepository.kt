package com.codeshail.pickmeupapp.domain.repositories

import com.codeshail.pickmeupapp.data.datasources.local.entity.WatchListEntity
import com.codeshail.pickmeupapp.domain.models.WatchProviders
import com.codeshail.pickmeupapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface WatchListInfoRepository {

    fun getWatchListInfo(): Flow<List<WatchListEntity>>
    suspend fun insertWatchListInfo(watchListEntity: WatchListEntity)

    suspend fun deleteWatchListInfo(watchListEntity: WatchListEntity)

    fun getMovieWatchProviders(movieId: Int): Flow<NetworkResult<WatchProviders>>

}