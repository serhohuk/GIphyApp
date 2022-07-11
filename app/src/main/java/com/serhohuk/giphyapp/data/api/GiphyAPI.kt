package com.serhohuk.giphyapp.data.api

import com.serhohuk.giphyapp.data.models.GifResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPI {

    @GET("trending")
    suspend fun trendingGifs(
        @Query("api_key")
        apiKey : String,
        @Query("limit")
        limit : Int,
        @Query("offset")
        offset : Int
    ) : Response<GifResponse>

    @GET("search")
    suspend fun searchGifs(
        @Query("api_key")
        apiKey : String,
        @Query("q")
        query : String,
        @Query("limit")
        limit : Int,
        @Query("offset")
        offset : Int
    ) : Response<GifResponse>

}