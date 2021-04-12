package com.study.mybookshelf.model

import io.realm.RealmModel
import java.io.Serializable

interface Book: RealmModel, Serializable {
    var id: Int
    var title: String
    var author: String
    var photo: ByteArray
    var rating: Float
    var isDigital: Boolean
    var comments: String
}