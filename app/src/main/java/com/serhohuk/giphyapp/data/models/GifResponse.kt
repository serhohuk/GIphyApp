package com.serhohuk.giphyapp.data.models

data class GifResponse(
    var `data`: List<Data>?,
    var meta: Meta?,
    var pagination: Pagination?
)