package com.serhohuk.giphyapp.domain.repository

import com.serhohuk.giphyapp.data.utils.Resource
import com.serhohuk.giphyapp.domain.models.GifData

interface GifRepository {

    suspend fun getTrendingGifs(apiKey : String, limit : Int, offset : Int) : Resource<GifData>

    suspend fun getSearchGifs(apiKey : String, query : String, limit : Int, offset : Int) : Resource<GifData>

}