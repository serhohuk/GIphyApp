package com.serhohuk.giphyapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhohuk.giphyapp.data.utils.Resource
import com.serhohuk.giphyapp.domain.models.GifData
import com.serhohuk.giphyapp.domain.usecase.SearchGifsUseCase
import com.serhohuk.giphyapp.domain.usecase.TrendingGifUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val trendingGifUseCase: TrendingGifUseCase,
    private val searchGifsUseCase: SearchGifsUseCase
) : ViewModel() {

    val LIMIT = 26
    private val gifsFlow : MutableStateFlow<Resource<GifData>> = MutableStateFlow(Resource.Empty())
    val gifsData = gifsFlow.asStateFlow()

    fun getTrendingGif(offset : Int){
        gifsFlow.value = Resource.Loading()
        viewModelScope.launch {
            gifsFlow.value = trendingGifUseCase.execute(LIMIT, offset)
        }
    }

    fun getSearchGif(query : String, offset: Int){
        gifsFlow.value = Resource.Loading()
        viewModelScope.launch {
            gifsFlow.value = searchGifsUseCase.execute(query, LIMIT, offset)
        }
    }

}