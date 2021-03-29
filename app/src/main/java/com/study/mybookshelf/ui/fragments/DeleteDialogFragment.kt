package com.study.mybookshelf.ui.fragments
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.model.LibraryBook
import io.realm.Realm


class DeleteDialogFragment(book: Book) : DialogFragment() {
   private val dbook=book

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.message)
            builder.setMessage(R.string.del_message)

            builder.setPositiveButton(R.string.yes) { dialog, which ->
                val realm: Realm = Realm.getDefaultInstance()
                realm.executeTransaction { realm ->
                if (dbook is BorrowedBook) {
                    val delbook = realm.where(BorrowedBook::class.java).equalTo("id", dbook.id).findFirst()
                    delbook?.deleteFromRealm()
                    }
                 if (dbook is LendedBook) {
                     val delbook = realm.where(LendedBook::class.java).equalTo("id", dbook.id).findFirst()
                     delbook?.deleteFromRealm()
                 }
                 if (dbook is LibraryBook) {
                     val delbook = realm.where(LibraryBook::class.java).equalTo("id", dbook.id).findFirst()
                     delbook?.deleteFromRealm()
                 }
                }
                requireActivity().onBackPressed()
            }

            builder.setNegativeButton(R.string.no) { dialog, which ->
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}