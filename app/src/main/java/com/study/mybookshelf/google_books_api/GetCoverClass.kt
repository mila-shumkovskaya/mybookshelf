package com.study.mybookshelf.google_books_api

import android.util.Log
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val TAG = "GOOGLE_BOOKS_API"

class GetCoverClass() {
    fun getCover() {
        val request = ServiceBuilder.buildService(ApiInterface::class.java)
        val call = request.getBooks("Harry Potter")
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful){
                    val responseBody: JsonObject? = response.body()
                    if (responseBody?.get("items") != null) {
                        Log.i(TAG, responseBody.toString())
                    }
                    else {
                        Log.i(TAG, responseBody.toString())
                    }
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.message?.let { Log.e(TAG, it) }
            }
        })
    }
}