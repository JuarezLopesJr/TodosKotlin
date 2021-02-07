package com.example.todoskotlin.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

object TodoRetrofitService {
    val api: TodoRetrofitApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoRetrofitApi::class.java)
    }
}