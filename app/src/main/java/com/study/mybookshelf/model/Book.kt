package com.study.mybookshelf.model

import io.realm.RealmModel

interface Book: RealmModel {
    var title: String
    var author: String
    var photo: Int
    var rating: Float
    var isDigital: Boolean
    var comments: String
}