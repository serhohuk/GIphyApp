package com.serhohuk.giphyapp.presentation.adapters

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serhohuk.giphyapp.R
import com.serhohuk.giphyapp.domain.models.Gif

class GifItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val image : ImageView = itemView.findViewById(R.id.iv_gif)

    fun bind(gif : Gif){
        Glide.with(image).asGif().load(gif.images!!.downsized!!.url)
            .into(image)
    }

}