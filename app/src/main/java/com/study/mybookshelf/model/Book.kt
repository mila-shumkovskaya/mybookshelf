package com.study.mybookshelf.model

import io.realm.RealmModel
import java.io.Serializable

interface Book: RealmModel, Serializable {
    var title: String
    var author: String
    var photo: Int
    var rating: Float
    var isDigital: Boolean
    var comments: String
}