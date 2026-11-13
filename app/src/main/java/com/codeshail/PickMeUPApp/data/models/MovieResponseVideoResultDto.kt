package com.codeshail.pickmeupapp.data.models

import com.codeshail.pickmeupapp.domain.models.MovieVideoResult

data class MovieResponseVideoResultDto(
    val id: String?,
    val key: String?,
    val name: String?,
    val official: Boolean?,
    val site: String?,
    val size: Int?,
    val type: String?
) {
    fun toMovieVideoResult(): MovieVideoResult {
        return MovieVideoResult(name = name, key = key, site = site, type = type)
    }
}