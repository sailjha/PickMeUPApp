package com.codeshail.pickmeupapp.data.models

import com.codeshail.pickmeupapp.domain.models.MovieList
import com.google.gson.annotations.SerializedName

data class MovieResponseListDto(
    val page: Int?,
    val results: List<MovieResponseResultDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
) {
    fun toMovieList(): MovieList {
        return MovieList(results?.map { it.toMovieResult() })
    }
}