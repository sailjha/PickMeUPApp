package com.codeshail.pickmeupapp.data.models

import com.codeshail.pickmeupapp.domain.models.HomeFeed


data class HomeFeedResponseDto(
    val title: String,
    val list: List<MovieResponseResultDto>
) {
    fun toHomeFeed(): HomeFeed {
        return HomeFeed(title = title, list = list.map { it.toMovieResult() })
    }
}
