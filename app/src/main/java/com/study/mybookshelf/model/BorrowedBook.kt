package com.study.mybookshelf.model

import android.content.res.Resources
import android.os.Parcelable
import androidx.core.graphics.drawable.toBitmap
import com.study.mybookshelf.R
import com.study.mybookshelf.utils.toByteArray
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import io.realm.RealmObject as RealmObject1

open class BorrowedBook (
    @PrimaryKey override var title: String = "",
    override var author: String = "",
    override var photo: ByteArray = ByteArray(0),
    override var rating: Float = 0.0.toFloat(),
    override var isDigital: Boolean = false,
    override var comments: String = "",

    var owner: String = "",
    var returnDate: String = "",
    var receiveDate: String = ""
) : Book, Serializable, RealmObject1()