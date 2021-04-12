package com.study.mybookshelf.ui.fragments

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.study.mybookshelf.R
import com.study.mybookshelf.REQUEST_CODE_CAMERA
import com.study.mybookshelf.REQUEST_CODE_GALLERY
import com.study.mybookshelf.REQUEST_CODE_INTERNET
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.ui.preferences.SharedPreferencesId
import com.study.mybookshelf.utils.*
import com.study.mybookshelf.ui_utils.*
import io.realm.Realm


class LibraryBookDetailsFragment: Fragment() {

    lateinit var ivCover: ImageView
    private lateinit var etTitle: EditText
    private lateinit var etAuthor: EditText
    private lateinit var rbRating: RatingBar
    private lateinit var switchIsEl: Switch
    private lateinit var etComment: EditText

    private lateinit var delete: ImageButton
    private lateinit var edit: ImageButton
    private lateinit var save: Button

    lateinit var book: Book

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_library_book_details, container, false)
        book = requireActivity().intent.getSerializableExtra("book") as Book
        val add = requireActivity().intent.getBooleanExtra("add", false)

    // find views by id
        ivCover = root.findViewById(R.id.iv_book_cover)
        etTitle = root.findViewById(R.id.et_title)
        etAuthor = root.findViewById(R.id.et_author)
        rbRating = root.findViewById(R.id.rating_bar)
        switchIsEl = root.findViewById(R.id.switch_is_el)
        etComment = root.findViewById(R.id.et_comment)

        delete = root.findViewById(R.id.bt_delete)
        edit = root.findViewById(R.id.bt_edit)
        save = root.findViewById(R.id.bt_save4)

    // set on text change validation
        etTitle.validate(getString(R.string.validation_title_message_begin) + SHORT_STRING_MAX_LENGTH + getString(R.string.validation_title_message_end))
        {str -> str.isValidShortNotEmpty()}

        etAuthor.validate(getString(R.string.validation_message_begin) + SHORT_STRING_MAX_LENGTH + getString(R.string.validation_message_end))
        {str -> str.isValidShort()}

        etComment.validate(getString(R.string.validation_message_begin) + LONG_STRING_MAX_LENGTH + getString(R.string.validation_message_end))
        {str -> str.isValidLong()}

    // set onClickListeners
        ivCover.setOnClickListener {
            if (etAuthor.isEnabled) {
                val coverDialogFragment = CoverDialogFragment(requireContext(), etTitle.text.toString(), etAuthor.text.toString())
                val manager = (context as AppCompatActivity).supportFragmentManager
                coverDialogFragment.setTargetFragment(this, REQUEST_CODE_INTERNET)
                coverDialogFragment.show(manager, "coverDialogFragment")
            }
        }

        delete.setOnClickListener {
            val myDialogFragment = DeleteDialogFragment(book)
            val manager = (context as AppCompatActivity).supportFragmentManager
            myDialogFragment.show(manager, "myDialog")
        }

        edit.setOnClickListener {
            val params1 = edit.layoutParams
            params1.height = 0
            edit.layoutParams = params1
            val params2 = delete.layoutParams
            params2.height = 0
            delete.layoutParams = params2
            val params = save.layoutParams
            params.height = ActionBar.LayoutParams.WRAP_CONTENT
            save.layoutParams = params

            setBookInfoFieldsEnabled(true, etTitle, etAuthor, rbRating, switchIsEl, etComment)
        }

        save.setOnClickListener {
            // get data
            val id = book.id
            book = getLibraryBookFromFields(etTitle, etAuthor, ivCover, rbRating, switchIsEl,
                etComment)
            book.id = id

            // validate book info
            if (book.title.isValidShortNotEmpty() && book.author.isValidShort()
                && book.comments.isValidLong()) {
                // save to realm
                if (add) {
                    SharedPreferencesId(requireContext()).saveId(id)
                }
                val realm: Realm = Realm.getDefaultInstance()
                realm.executeTransaction { realmDB ->
                    realmDB.insertOrUpdate(book)
                }
                requireActivity().onBackPressed()
            }
            else {
                Toast.makeText(activity, R.string.book_not_valid, Toast.LENGTH_LONG).show()
            }
        }

    // set up fields
        if (!add) {
            setBookInfoToFields(book, false, etTitle, etAuthor, ivCover, rbRating, switchIsEl, etComment)
        }
        else {
            setBookInfoToFields(book, true, etTitle, etAuthor, ivCover, rbRating, switchIsEl, etComment)
        }

        if (!add) {
            val params = save.layoutParams
            params.height = 0
            save.layoutParams = params

            setBookInfoFieldsEnabled(false, etTitle, etAuthor, rbRating, switchIsEl, etComment)
        }
        else {
            val params1 = edit.layoutParams
            params1.height = 0
            edit.layoutParams = params1
            val params2 = delete.layoutParams
            params2.height = 0
            delete.layoutParams = params2
        }

        return root
    }

//    private fun getInfoFromFields(): LibraryBook {
//        val modifiedBook = LibraryBook()
//
//        modifiedBook.title = etTitle.text.toString()
//        modifiedBook.author = etAuthor.text.toString()
//        modifiedBook.photo = ivCover.drawable.toBitmap().resize()!!.toByteArray()
//        modifiedBook.rating = rbRating.rating
//        modifiedBook.isDigital = switchIsEl.isChecked
//        modifiedBook.comments = etComment.text.toString()
//
//        return modifiedBook
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i(this.tag, "onActivityResult")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CAMERA && data != null) {
            val thumbnailBitmap = data.extras?.get("data") as Bitmap?
            if (thumbnailBitmap != null) {
                Log.i(this.tag, "bitmap is set")
                ivCover.setImageBitmap(thumbnailBitmap.resize())
            } else {
                Log.i(this.tag, "bitmap is null")
            }
        }
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_GALLERY && data != null) {
            val selectedImage: Uri = data.data!!
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

            val cursor: Cursor = requireContext().contentResolver.query(selectedImage, filePathColumn, null, null, null)!!
            cursor.moveToFirst()

            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
            val filePath: String = cursor.getString(columnIndex)
            cursor.close()

            val bitmap = BitmapFactory.decodeFile(filePath)
            ivCover.setImageBitmap(bitmap.resize())
        }
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_INTERNET && data != null) {
            val thumbnailBitmap = (data.extras?.get("image") as ByteArray).toBitmap()
            if (thumbnailBitmap != null) {
                Log.i(this.tag, "bitmap is set")
                ivCover.setImageBitmap(thumbnailBitmap.resize())
            } else {
                Log.i(this.tag, "bitmap is null")
            }
        }
    }
}