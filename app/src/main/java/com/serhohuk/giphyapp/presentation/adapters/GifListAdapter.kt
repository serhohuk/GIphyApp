package com.serhohuk.giphyapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.serhohuk.giphyapp.R
import com.serhohuk.giphyapp.domain.models.Gif
import com.serhohuk.giphyapp.presentation.utils.AdapterType

class GifListAdapter(private val adapterType: AdapterType) : PagingDataAdapter<Gif,RecyclerView.ViewHolder>(GifDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (adapterType == AdapterType.LIST_ADAPTER){
            GifItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gif,parent,false))
        } else GifItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gif_full,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GifItemViewHolder).bind(getItem(position)!!)
        (holder as GifItemViewHolder).setListener {
            listener?.let {
                it(Pair(position,getItem(position)!!))
            }
        }
    }


    private object GifDiffItemCallback : DiffUtil.ItemCallback<Gif>(){

        override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem == newItem
        }

    }

    private var listener : ((Pair<Int,Gif>) -> Unit)? = null

    fun setListener(listener : (Pair<Int,Gif>)->Unit){
        this.listener = listener
    }

}