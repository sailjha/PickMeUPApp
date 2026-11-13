package com.codeshail.pickmeupapp.data.datasources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codeshail.pickmeupapp.domain.models.HomeFeed
import com.codeshail.pickmeupapp.domain.models.HomeFeedData
import com.codeshail.pickmeupapp.domain.models.MovieResult
import com.codeshail.pickmeupapp.utils.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class MovieDataEntity(
    val homeFeedList: List<HomeFeed>,
    val bannerMovie: List<MovieResult>,
    @PrimaryKey val id: Int? = null
) {

    fun toHomeFeedDataInfo(): HomeFeedData {
        return HomeFeedData(
            bannerMovie = bannerMovie,
            homeFeedResponseList = homeFeedList
        )
    }

}