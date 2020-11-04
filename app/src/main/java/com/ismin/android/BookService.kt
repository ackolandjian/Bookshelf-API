package com.ismin.android

import retrofit2.Call
import retrofit2.http.GET

interface BookService {

    @GET("books")
    fun getAllBooks(): Call<ArrayList<Book>>
}