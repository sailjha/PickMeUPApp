package com.codeshail.pickmeupapp.data.datasources.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.codeshail.pickmeupapp.data.models.MovieResponseResultDto
import com.codeshail.pickmeupapp.domain.models.HomeFeed
import com.codeshail.pickmeupapp.domain.models.MovieResult
import com.codeshail.pickmeupapp.utils.JsonParser
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class MovieDataTypeConverter(private val jsonParser: JsonParser) {

    @TypeConverter
    fun movieResultListDataToStringData(movieResult: List<MovieResult>): String {
        val listType = object : TypeToken<ArrayList<MovieResponseResultDto>>() {}.type
        return jsonParser.toJson(movieResult, listType) ?: "[]"
    }

    @TypeConverter
    fun stringToMovieResultListData(data: String): List<MovieResult> {
        val listType = object : TypeToken<ArrayList<MovieResult>>() {}.type
        return jsonParser.fromJson<ArrayList<MovieResult>>(
            data, listType
        ) ?: emptyList()
    }


    @TypeConverter
    fun movieResultDataToStringData(movieResult: MovieResult): String {
        val listType = object : TypeToken<MovieResult>() {}.type
        return jsonParser.toJson(movieResult, listType) ?: "[]"
    }

    @TypeConverter
    fun stringToMovieResultData(data: String): MovieResult? {
        val listType = object : TypeToken<MovieResult>() {}.type
        return jsonParser.fromJson<MovieResult>(
            data, listType
        )
    }


    @TypeConverter
    fun dataToStringHomeFeedData(homeFeedData: List<HomeFeed>): String {
        val listType = object : TypeToken<ArrayList<HomeFeed>>() {}.type
        return jsonParser.toJson(homeFeedData, listType) ?: "[]"
    }

    @TypeConverter
    fun stringToHomeFeedData(data: String): List<HomeFeed> {
        val listType = object : TypeToken<ArrayList<HomeFeed>>() {}.type
        return jsonParser.fromJson<ArrayList<HomeFeed>>(
            data, listType
        ) ?: emptyList()
    }

}