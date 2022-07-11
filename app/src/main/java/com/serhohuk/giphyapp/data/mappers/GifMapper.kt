package com.serhohuk.giphyapp.data.mappers

import com.serhohuk.giphyapp.data.models.GifResponse
import com.serhohuk.giphyapp.domain.models.Gif
import com.serhohuk.giphyapp.domain.models.GifData

object GifMapper {

    fun gifResponseToGifData(gifResponse: GifResponse): GifData {
        val list = mutableListOf<Gif>()
        gifResponse.data?.mapTo(list) {
            Gif(it.embed_url, it.id, it.images, it.is_sticker, it.title, it.url)
        }
        return GifData(list)
    }
}