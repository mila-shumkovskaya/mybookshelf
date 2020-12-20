package com.study.mybookshelf.model

import io.realm.annotations.PrimaryKey
import io.realm.RealmObject as RealmObject1

open class BorrowedBook (
    @PrimaryKey override var title: String = "",
    override var author: String = "",
    override var photo: Int = 0,
    override var rating: Float = 0.0.toFloat(),
    override var isDigital: Boolean = false,
    override var comments: String = "",

    var recipient: String = "",
    var returnDate: String = "",
    var transferDate: String = ""
) : Book, RealmObject1()