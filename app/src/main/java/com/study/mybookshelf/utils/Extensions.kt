package com.study.mybookshelf.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.getString(): String{
    var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return this.format(formatter)
}