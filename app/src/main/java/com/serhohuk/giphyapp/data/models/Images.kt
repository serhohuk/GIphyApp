package com.serhohuk.giphyapp.data.models

data class Images(
    var downsized: Downsized?,
    var downsized_still: DownsizedStill?,
    var fixed_width: FixedWidth?,
    var fixed_width_still: FixedWidthStill?,
    var preview: Preview?,
    var preview_gif: PreviewGif?
)