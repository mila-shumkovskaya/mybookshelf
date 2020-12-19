package com.study.mybookshelf.model

import java.time.LocalDate

data class BorrowedBook( //взяты
    var bbTitle: String,
    var bbAuthor: String,
    var bbPhoto: Int,
    var bbRating: Float,
    var bbIsDigital: Boolean,
    var bbComments: String,

    var owner: String,
    var returnDate: LocalDate,
    var receiveDate: LocalDate
) : Book(bbTitle, bbAuthor, bbPhoto, bbRating, bbIsDigital, bbComments) {

}
