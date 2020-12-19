package com.study.mybookshelf.model

import java.time.LocalDate

data class LendedBook(
    var lbTitle: String,
    var lbAuthor: String,
    var lbPhoto: Int,
    var lbRaiting: Float,
    var lbIsDigital: Boolean,
    var lbComments: String,

    var owner: String,
    var returnDate: LocalDate,
    var receiveDate: LocalDate
) : Book(lbTitle, lbAuthor, lbPhoto, lbRaiting, lbIsDigital, lbComments) {

}
