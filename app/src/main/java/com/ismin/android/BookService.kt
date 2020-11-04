package com.ismin.android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookService {

    @GET("books")
    fun getAllBooks(): Call<ArrayList<Book>>

    @POST("books")
    fun createBook(@Body() book: Book): Call<Book>
}