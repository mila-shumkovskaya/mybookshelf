package com.study.mybookshelf.model

import java.time.LocalDate

data class LendedBook( //отданы
    var lbTitle: String,
    var lbAuthor: String,
    var lbPhoto: Int,
    var lbRating: Float,
    var lbIsDigital: Boolean,
    var lbComments: String,

    var recipient: String,
    var returnDate: LocalDate,
    var transferDate: LocalDate
) : Book(lbTitle, lbAuthor, lbPhoto, lbRating, lbIsDigital, lbComments) {

}
