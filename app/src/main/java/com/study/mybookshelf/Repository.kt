package com.study.mybookshelf

import com.study.mybookshelf.utils.*
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.model.BorrowedBook
import java.util.Date

class Repository {

    fun getBooksList(bookType: BookType): List<Book> {
        val book1 = Book("title_1", "author_1", 1, 5.0.toFloat(), true, "useful comments")
        val book2 = Book("title_2", "author_2", 2, 3.0.toFloat(), false, "unuseful comments")
        val book3 = Book("title_3", "author_3", 3, 4.7.toFloat(), false, "best comment ever")

        val lended1 = LendedBook("lended_1", "author_1", 1, 5.0.toFloat(), true, "interesting book", "Petya", Date(2020-12-20), Date(2021-12-20))
        val lended2 = LendedBook("lended_2", "author_2", 2, 3.0.toFloat(), false, "hi!", "Kate&Leo", Date(2020-10-20), Date(2021-11-20))
        val lended3 = LendedBook("lended_3", "author_3", 3, 4.7.toFloat(), true, "lendeeed", "Smb", Date(2019-12-10), Date(2020-12-20))

        val borrowed1 = BorrowedBook("borrowed_1", "author_1", 1, 5.0.toFloat(), true, "hi there i'm using whatsApp", "Ted", Date(2019-12-10), Date(2020-12-20))
        val borrowed2 = BorrowedBook("borrowed_2", "author_2", 2, 3.0.toFloat(), false, "boRRRRRowed BOOOOOk", "Mya", Date(2018-12-10), Date(2022-12-20))

        val list: MutableList<Book> = arrayListOf()

        when (bookType) {
            BookType.BOOK -> {
                list.toMutableList().add(book1)
                list.toMutableList().add(book2)
                list.toMutableList().add(book3)
            }
            BookType.LENDED_BOOK -> {
                list.add(lended1)
                list.add(lended2)
                list.add(lended3)
            }
            BookType.BORROWED_BOOK -> {
                list.toMutableList().add(borrowed1)
                list.toMutableList().add(borrowed2)
            }
        }

        return list
    }

}