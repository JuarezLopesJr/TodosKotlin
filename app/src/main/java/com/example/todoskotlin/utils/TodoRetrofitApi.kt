package com.example.todoskotlin.utils

import com.example.todoskotlin.model.Todo
import retrofit2.Response
import retrofit2.http.GET

// lista de Todos do retorno da api vem da data class
interface TodoRetrofitApi {
    @GET("todos")
    suspend fun getTodos(): Response<List<Todo>>
}