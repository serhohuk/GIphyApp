package com.serhohuk.giphyapp.presentation.viewmodels

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.serhohuk.giphyapp.data.utils.Resource
import com.serhohuk.giphyapp.domain.models.Gif
import com.serhohuk.giphyapp.domain.models.GifData
import com.serhohuk.giphyapp.domain.usecase.SearchGifsUseCase
import com.serhohuk.giphyapp.domain.usecase.TrendingGifUseCase
import com.serhohuk.giphyapp.presentation.adapters.GifLoadPagingSource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val trendingGifUseCase: TrendingGifUseCase,
    private val searchGifsUseCase: SearchGifsUseCase
) : ViewModel() {

    var gifsData : MutableStateFlow<PagingData<Gif>> = MutableStateFlow(PagingData.empty())
    var position = 0
    var recyclerState : Parcelable? = null
    var searchText : String = ""

    fun searchGifs(query: String){
        gifsData.value = Pager(PagingConfig(26)){
            GifLoadPagingSource(trendingGifUseCase,searchGifsUseCase,query)
        }.flow.cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Eagerly, PagingData.empty()).value
    }

    fun trendingGifs(){
        gifsData.value = Pager(PagingConfig(26)){
            GifLoadPagingSource(trendingGifUseCase,searchGifsUseCase,null)
        }.flow.cachedIn(viewModelScope).stateIn(viewModelScope, SharingStarted.Eagerly, PagingData.empty()).value
    }

}