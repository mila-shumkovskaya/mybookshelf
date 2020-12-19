package com.study.mybookshelf.model

import java.util.Date

data class BorrowedBook(
    var lbTitle: String,
    var lbAuthor: String,
    var lbPhoto: Int,
    var lbRaiting: Float,
    var lbIsDigital: Boolean,
    var lbComments: String,

    var recipient: String,
    var returnDate: Date,
    var transferDate: Date
) : Book(lbTitle, lbAuthor, lbPhoto, lbRaiting, lbIsDigital, lbComments) {

}
