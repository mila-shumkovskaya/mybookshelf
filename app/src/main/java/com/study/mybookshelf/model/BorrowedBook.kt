package com.study.mybookshelf.model

import java.time.LocalDate

data class BorrowedBook(
    var lbTitle: String,
    var lbAuthor: String,
    var lbPhoto: Int,
    var lbRaiting: Float,
    var lbIsDigital: Boolean,
    var lbComments: String,

    var recipient: String,
    var returnDate: LocalDate,
    var transferDate: LocalDate
) : Book(lbTitle, lbAuthor, lbPhoto, lbRaiting, lbIsDigital, lbComments) {

}
