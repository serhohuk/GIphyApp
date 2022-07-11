package com.serhohuk.giphyapp.domain.models

import com.serhohuk.giphyapp.data.models.Images

data class Gif(var embed_url: String?,
               var id: String?,
               var images: Images?,
               var is_sticker: Int?,
               var title: String?,
               var url: String?)
