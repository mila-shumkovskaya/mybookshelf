package com.study.mybookshelf.google_books_api

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


const val TAG = "GOOGLE_BOOKS_API"
const val APIKey = "AIzaSyCnZ7TW26zQKW4wPHwPVUuYjBkHBQnWF40"

class GetCoverClass(val activity: Activity) {

    var livaDataJsonBooks: MutableLiveData<JsonBooks> = MutableLiveData()

    data class JsonBooks (
        val totalItems: Int,
        val items: List<JsonBook>
    )

    data class JsonBook (
        val volumeInfo: VolumeInfo
    )

    data class VolumeInfo (
        val imageLinks: ImageLinks
    )

    data class  ImageLinks (
        val smallThumbnail: String
    )

    fun getCover(title: String, author: String, returnImages: (activity: Activity, imageList: ArrayList<Bitmap>) -> Unit) {
        Log.i("GetCoverClass", "getCover()")
        if (title.isNullOrBlank()) {
            returnImages(activity, arrayListOf<Bitmap>())
            return
        }
        var callString = "intitle:$title"
        if (author.isNotBlank())
            callString += "+inauthor:$author"

        val request = ServiceBuilder.buildService(ApiInterface::class.java)
        val call = request.getBooks(callString, APIKey)
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val responseBody: JsonObject? = response.body()
                    if (responseBody != null) {
                        Log.i(TAG, responseBody.toString())
                        val gson = Gson()
                        livaDataJsonBooks.value = gson.fromJson(responseBody, JsonBooks::class.java)
                        //Log.i(this.toString(), livaDataJsonBooks.value!!.items[0].volumeInfo.imageLink.smallThumbnail)
                    } else {
                        Log.i(TAG, "response.body is NULL")
                    }

                    val jsonBooks = livaDataJsonBooks.value!!
                    val imageList = arrayListOf<Bitmap>()
                    if (jsonBooks.totalItems != 0) {
                        try {
                            Log.i("GetCoverClass", "new thread")
                            for (item in jsonBooks.items) {
                                val url = URL(item.volumeInfo.imageLinks.smallThumbnail)
                                //val input: InputStream = url.content as InputStream
                                val bitmap = BitmapFactory.decodeStream(url.openConnection().apply {
                                    doInput = true
                                    connect()
                                }.getInputStream())
                                if (bitmap != null) {
                                    imageList.add(bitmap)
                                    if (imageList.size > 9) {
                                        break
                                    }
                                }
                            }
                            Log.i("GetCoverClass", imageList.toString())

                        } catch (e: Exception) {
                            //Log.i("GetCoverClass", item.toString() + "is NULL")
                            Log.i("GetCoverClass", e.message.toString())
                        }
                    }
                    returnImages(activity, imageList)
                }
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.message?.let { Log.e(TAG, it) }
            }
        })
    }
}
