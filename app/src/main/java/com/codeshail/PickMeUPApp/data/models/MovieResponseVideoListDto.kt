package com.codeshail.pickmeupapp.data.models

import com.codeshail.pickmeupapp.domain.models.MovieVideoList

data class MovieResponseVideoListDto(
    val id: Int?,
    val results: List<MovieResponseVideoResultDto>?
) {
    fun toMovieVideoList(): MovieVideoList {
        return MovieVideoList(id, results?.map { it.toMovieVideoResult() })
    }
}