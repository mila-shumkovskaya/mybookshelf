package com.study.mybookshelf.model

import java.time.LocalDate

data class BorrowedBook(
    var bbTitle: String,
    var bbAuthor: String,
    var bbPhoto: Int,
    var bbRating: Float,
    var bbIsDigital: Boolean,
    var bbComments: String,

    var recipient: String,
    var returnDate: LocalDate,
    var transferDate: LocalDate
) : Book(bbTitle, bbAuthor, bbPhoto, bbRating, bbIsDigital, bbComments) {

}
