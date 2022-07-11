package com.serhohuk.giphyapp.domain.usecase

import com.serhohuk.giphyapp.data.utils.Resource
import com.serhohuk.giphyapp.domain.models.GifData
import com.serhohuk.giphyapp.domain.repository.GifRepository

class TrendingGifUseCase(private val repository: GifRepository) {

    suspend fun execute(limit : Int, offset : Int): Resource<GifData> {
        return repository.getTrendingGifs(limit, offset)
    }
}