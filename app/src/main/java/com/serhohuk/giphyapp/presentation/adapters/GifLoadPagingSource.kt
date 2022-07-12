package com.serhohuk.giphyapp.presentation.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.serhohuk.giphyapp.data.utils.Resource
import com.serhohuk.giphyapp.domain.models.Gif
import com.serhohuk.giphyapp.domain.repository.GifRepository
import com.serhohuk.giphyapp.domain.usecase.SearchGifsUseCase
import com.serhohuk.giphyapp.domain.usecase.TrendingGifUseCase
import java.lang.IllegalArgumentException

class GifLoadPagingSource(
    private val trendingGifUseCase: TrendingGifUseCase,
    private val searchGifsUseCase: SearchGifsUseCase,
    private val query : String?
) : PagingSource<Int, Gif>() {

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val page = params.key ?: 1
        val pageSize = 26

        val response = if (query == null) {
            trendingGifUseCase.execute(pageSize, page * pageSize - pageSize)
        } else {
            searchGifsUseCase.execute(query, pageSize, page * pageSize - pageSize)
        }
        return if (response is Resource.Success) {
            val gifsData = response.data!!.list
            val nextKey = if (gifsData.size < 26) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(gifsData, prevKey, nextKey)
        } else {
            LoadResult.Error(IllegalArgumentException())
        }
    }
}