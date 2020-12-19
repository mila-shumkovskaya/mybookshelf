package com.study.mybookshelf.model

import java.util.Date

data class LendedBook(
    var lbTitle: String,
    var lbAuthor: String,
    var lbPhoto: Int,
    var lbRaiting: Float,
    var lbIsDigital: Boolean,
    var lbComments: String,

    var owner: String,
    var returnDate: Date,
    var receiveDate: Date
) : Book(lbTitle, lbAuthor, lbPhoto, lbRaiting, lbIsDigital, lbComments) {

}
