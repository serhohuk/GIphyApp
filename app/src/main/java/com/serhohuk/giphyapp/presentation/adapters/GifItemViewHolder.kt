package com.serhohuk.giphyapp.presentation.adapters

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serhohuk.giphyapp.R
import com.serhohuk.giphyapp.domain.models.Gif
import com.serhohuk.giphyapp.presentation.utils.AdapterType

class GifItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val image : ImageView = itemView.findViewById(R.id.iv_gif)

    private var listener : ((Gif)-> Unit)? = null

    fun bind(gif : Gif){
        Glide.with(image).asGif().load(gif.images!!.downsized!!.url)
            .placeholder(R.drawable.placeholder_loading)
            .into(image)
        image.setOnClickListener {
            listener?.let {
                it(gif)
            }
        }
    }

    fun setListener(listener : (Gif) -> Unit){
        this.listener = listener
    }
}