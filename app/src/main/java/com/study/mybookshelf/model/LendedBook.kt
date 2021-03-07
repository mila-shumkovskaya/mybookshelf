package com.study.mybookshelf.model

import android.content.res.Resources
import androidx.core.graphics.drawable.toBitmap
import com.study.mybookshelf.R
import com.study.mybookshelf.utils.toByteArray
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class LendedBook(
    @PrimaryKey override var id: Int = 0,
    override var title: String = "",
    override var author: String = "",
    override var photo: ByteArray = ByteArray(0),
    override var rating: Float = 0.0.toFloat(),
    override var isDigital: Boolean = false,
    override var comments: String = "",

    var recipient: String = "",
    var returnDate: String = "",
    var transferDate: String = ""
) : Book, Serializable, RealmObject()