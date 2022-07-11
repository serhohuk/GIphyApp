package com.serhohuk.giphyapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitGiphy {

    private const val GIPHY_BASE_URL = "https://api.giphy.com/v1/"
    const val API_KEY = "DqtG6vJauVhv8HSFtBvz3qdeMeFFUqEu"

    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(GIPHY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val giphyAPI = retrofit.create(GiphyAPI::class.java)

}