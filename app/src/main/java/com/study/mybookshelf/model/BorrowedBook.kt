package com.study.mybookshelf.model

import java.util.Date

data class BorrowedBook(
    var bbTitle: String,
    var bbAuthor: String,
    var bbPhoto: Int,
    var bbRating: Float,
    var bbIsDigital: Boolean,
    var bbComments: String,

    var recipient: String,
    var returnDate: Date,
    var transferDate: Date
) : Book(bbTitle, bbAuthor, bbPhoto, bbRating, bbIsDigital, bbComments) {

}
