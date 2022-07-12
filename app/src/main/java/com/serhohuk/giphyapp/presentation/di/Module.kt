package com.serhohuk.giphyapp.presentation.di

import com.serhohuk.giphyapp.data.repositoriesImpl.GifRepositoryImpl
import com.serhohuk.giphyapp.domain.repository.GifRepository
import com.serhohuk.giphyapp.domain.usecase.SearchGifsUseCase
import com.serhohuk.giphyapp.domain.usecase.TrendingGifUseCase
import com.serhohuk.giphyapp.presentation.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repoModule = module {

    factory<GifRepository>{
        GifRepositoryImpl()
    }

    factory {
        SearchGifsUseCase(get())
    }

    factory {
        TrendingGifUseCase(get())
    }
}

val viewModelModule = module {

    viewModel{
        MainViewModel(get(), get())
    }
}

