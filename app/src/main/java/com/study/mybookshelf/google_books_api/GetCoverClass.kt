package com.study.mybookshelf.google_books_api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.URL

const val TAG = "GOOGLE_BOOKS_API"
const val APIKey = "AIzaSyCnZ7TW26zQKW4wPHwPVUuYjBkHBQnWF40"

class GetCoverClass() {

    var livaDataJsonBooks: MutableLiveData<JsonBooks> = MutableLiveData()

    data class JsonBooks (
        val totalItems: Int,
        val items: List<JsonBook>
    ) {
        fun getBitmapArrayList(): ArrayList<Bitmap> {
            val imageList = arrayListOf<Bitmap>()
            for (item in items) {
                val bitmap: Bitmap? = item.volumeInfo.imageLink.getBitmap()
                if (bitmap != null) {
                    imageList.add(bitmap)
                }
            }
            return imageList
        }
    }

    data class JsonBook (
        val volumeInfo: VolumeInfo
    )

    data class VolumeInfo (
        val imageLink: ImageLink
    ) /*{
        fun getSmallThumbnail(): String? {
            return imageLinks.get("smallThumbnail")?.asString
        }

        fun getThumbnail(): String? {
            return imageLinks?.get("thumbnail")?.asString
        }
    }*/

    data class  ImageLink (
        val smallThumbnail: String
    ) {
        fun getBitmap(): Bitmap? {
            return try {
                val url = URL(smallThumbnail)
                BitmapFactory.decodeStream(url.openConnection().getInputStream())
            } catch (e: IOException) {
                e.message?.let { Log.e(TAG, it) }
                null
            }
        }
    }


    fun getCover(title: String, author: String) {
        val request = ServiceBuilder.buildService(ApiInterface::class.java)
        val call = request.getBooks("intitle:" + title + "+inauthor:" + author, APIKey)
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful){
                    val responseBody: JsonObject? = response.body()
                    if (responseBody != null) {
                        Log.i(TAG, responseBody.toString())
                        val gson = Gson()
                        livaDataJsonBooks.value = gson.fromJson(responseBody, JsonBooks::class.java)
                        //Log.i(this.toString(), livaDataJsonBooks.value!!.items[0].volumeInfo.imageLink.smallThumbnail)
                    }
                    else {
                        Log.i(TAG, "response.body is NULL")
                    }
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.message?.let { Log.e(TAG, it) }
            }
        })
    }
}