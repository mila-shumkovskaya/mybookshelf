package com.study.mybookshelf.model

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SimpleDateFormat")
fun LocalDate.getString(): String{
    var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return this.format(formatter)
}