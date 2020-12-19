package com.study.mybookshelf

import android.os.Build
import androidx.annotation.RequiresApi
import com.study.mybookshelf.utils.*
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.model.BorrowedBook
import java.time.LocalDate
import java.util.Date

class Repository {

    fun getBooksList(bookType: BookType): List<Book> {
        val book1 = Book("title_1", "author_1", R.mipmap.book_cover, 5.0.toFloat(), true, "useful comments")
        val book2 = Book("title_2", "author_2", R.mipmap.book_cover, 3.0.toFloat(), false, "unuseful comments")
        val book3 = Book("title_3", "author_3", R.mipmap.book_cover, 4.7.toFloat(), false, "best comment ever")
        val book4 = (Book("HarryPotter1", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        val book5 = (Book("HarryPotter2", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        val book6 = (Book("HarryPotter3", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        val book7 = (Book("HarryPotter4", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        val book8 = (Book("HarryPotter5", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        val book9 = (Book("HarryPotter6", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        val book10 = (Book("HarryPotter7", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))


        val lended1 = LendedBook("lended_1", "author_1", R.mipmap.book_cover, 5.0.toFloat(), true, "interesting book",
                                 "Petya", LocalDate.of(2020, 12, 20), LocalDate.of(2021, 12, 20))
        val lended2 = LendedBook("lended_2", "author_2", R.mipmap.book_cover, 3.0.toFloat(), false, "hi!",
                                 "Kate&Leo", LocalDate.of(2020, 10, 20), LocalDate.of(2021, 11, 20))
        val lended3 = LendedBook("lended_3", "author_3", R.mipmap.book_cover, 4.7.toFloat(), true, "lendeeed",
                                 "Smb", LocalDate.of(2019, 12, 10), LocalDate.of(2020, 12, 20))
        val lended4 = (LendedBook("HarryPotter1", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val lended5 = (LendedBook("HarryPotter2", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val lended6 = (LendedBook("HarryPotter3", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val lended7 = (LendedBook("HarryPotter4", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val lended8 = (LendedBook("HarryPotter5", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val lended9 = (LendedBook("HarryPotter6", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val lended10 = (LendedBook("HarryPotter7", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))


        val borrowed1 = BorrowedBook("borrowed_1", "author_1", R.mipmap.book_cover, 5.0.toFloat(), true, "hi there i'm using whatsApp",
                                    "Ted", LocalDate.of(2019, 12, 10), LocalDate.of(2020, 12, 20))
        val borrowed2 = BorrowedBook("borrowed_2", "author_2", R.mipmap.book_cover, 3.0.toFloat(), false, "boRRRRRowed BOOOOOk",
                                    "Mya", LocalDate.of(2018, 12, 10), LocalDate.of(2022, 12, 20))
        val borrowed3 = (BorrowedBook("HarryPotter1", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val borrowed4 = (BorrowedBook("HarryPotter2", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val borrowed5 = (BorrowedBook("HarryPotter3", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val borrowed6 = (BorrowedBook("HarryPotter4", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val borrowed7 = (BorrowedBook("HarryPotter5", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val borrowed8 = (BorrowedBook("HarryPotter6", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        val borrowed9 = (BorrowedBook("HarryPotter7", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))


        val list: MutableList<Book> = arrayListOf()

        when (bookType) {
            BookType.BOOK -> {
                list.add(book1)
                list.add(book2)
                list.add(book3)
                list.add(book4)
                list.add(book5)
                list.add(book6)
                list.add(book7)
                list.add(book8)
                list.add(book9)
                list.add(book10)
            }
            BookType.LENDED_BOOK -> {
                list.add(lended1)
                list.add(lended2)
                list.add(lended3)
                list.add(lended4)
                list.add(lended5)
                list.add(lended6)
                list.add(lended7)
                list.add(lended8)
                list.add(lended9)
                list.add(lended10)
            }
            BookType.BORROWED_BOOK -> {
                list.add(borrowed1)
                list.add(borrowed2)
                list.add(borrowed3)
                list.add(borrowed4)
                list.add(borrowed5)
                list.add(borrowed6)
                list.add(borrowed7)
                list.add(borrowed8)
                list.add(borrowed9)
            }
        }

        return list
    }

}