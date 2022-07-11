package com.serhohuk.giphyapp.data.repositoriesImpl

import com.serhohuk.giphyapp.data.api.RetrofitGiphy.API_KEY
import com.serhohuk.giphyapp.data.api.RetrofitGiphy.giphyAPI
import com.serhohuk.giphyapp.data.mappers.GifMapper
import com.serhohuk.giphyapp.data.utils.Resource
import com.serhohuk.giphyapp.domain.models.GifData
import com.serhohuk.giphyapp.domain.repository.GifRepository

class GifRepositoryImpl : GifRepository {

    override suspend fun getTrendingGifs(
        limit: Int,
        offset: Int
    ): Resource<GifData> {
        val response = giphyAPI.trendingGifs(API_KEY,limit, offset)
        if (response.isSuccessful){
            val gifData = GifMapper.gifResponseToGifData(response.body()!!)
            return Resource.Success(gifData)
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchGifs(
        query: String,
        limit: Int,
        offset: Int
    ): Resource<GifData> {
        val response = giphyAPI.searchGifs(API_KEY,query,limit, offset)
        if (response.isSuccessful){
            val gifData = GifMapper.gifResponseToGifData(response.body()!!)
            return Resource.Success(gifData)
        }
        return Resource.Error(response.message())
    }
}