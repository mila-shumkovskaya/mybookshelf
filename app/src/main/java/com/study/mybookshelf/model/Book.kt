package com.study.mybookshelf.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


open class Book(
    var title: String,
    var author: String,
    var photo: Int,
    var rating: Float,
    var isDigital: Boolean,
    var comments: String):Serializable{
}