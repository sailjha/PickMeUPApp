package com.codeshail.pickmeupapp.data.datasources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codeshail.pickmeupapp.domain.models.MovieResult
import com.codeshail.pickmeupapp.utils.Constants.WATCHLIST_TABLE_NAME

@Entity(tableName = WATCHLIST_TABLE_NAME)
data class WatchListEntity(
    val bannerMovie: MovieResult,
    @PrimaryKey val id: Int
)