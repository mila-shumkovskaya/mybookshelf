package com.study.mybookshelf.google_books_api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("volumes")
    fun getBooks(@Query("q") id: String?): Call<JsonObject>
}