package com.codeshail.pickmeupapp.data.models

import com.codeshail.pickmeupapp.domain.models.IN
import com.codeshail.pickmeupapp.domain.models.Results
import com.codeshail.pickmeupapp.domain.models.WatchProviders
import com.google.gson.annotations.SerializedName


data class WatchProvidersResponse(
    var id: Int? = null,
    var results: ResultsResponse? = null
) {
    fun toWatchProviders(): WatchProviders {
        return WatchProviders(id = id, results = results?.toResult())
    }
}


data class ResultsResponse(
    @SerializedName("IN")
    var iN: INResponse? = null
) {
    fun toResult(): Results {
        return Results(iN = iN?.toIn())
    }
}

data class INResponse(
    val link: String?,
) {
    fun toIn(): IN {
        return IN(link = link)
    }
}