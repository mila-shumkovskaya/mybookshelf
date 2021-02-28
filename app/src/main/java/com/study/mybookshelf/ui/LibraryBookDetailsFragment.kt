package com.study.mybookshelf.ui

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
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.study.mybookshelf.R
import com.study.mybookshelf.REQUEST_CODE_CAMERA
import com.study.mybookshelf.REQUEST_CODE_GALLERY
import com.study.mybookshelf.REQUEST_CODE_INTERNET
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.LibraryBook
import com.study.mybookshelf.utils.resize
import com.study.mybookshelf.utils.toBitmap
import com.study.mybookshelf.utils.toByteArray
import io.realm.Realm


class LibraryBookDetailsFragment: Fragment() {

    private lateinit var ivCover: ImageView
    private lateinit var etTitle: EditText
    private lateinit var etAuthor: EditText
    private lateinit var rbRating: RatingBar
    private lateinit var switchIsEl: Switch
    private lateinit var etComment: EditText

    lateinit var book: Book

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       val root = inflater.inflate(R.layout.fragment_library_book_details, container, false)
       book = requireActivity().intent.getSerializableExtra("book") as Book
       val add=requireActivity().intent.getBooleanExtra("add", false)

       ivCover = root.findViewById(R.id.iv_book_cover)
       etTitle = root.findViewById(R.id.et_title)
       etAuthor = root.findViewById(R.id.et_author)
       rbRating = root.findViewById(R.id.rating_bar)
       switchIsEl = root.findViewById(R.id.switch_is_el)
       etComment = root.findViewById(R.id.et_comment)

       ivCover.setOnClickListener {
           if (etAuthor.isEnabled) {
               val coverDialogFragment = CoverDialogFragment(requireContext(), etTitle.text.toString(), etAuthor.text.toString())
               val manager = (context as AppCompatActivity).supportFragmentManager
               coverDialogFragment.setTargetFragment(this, REQUEST_CODE_INTERNET)
               coverDialogFragment.show(manager, "coverDialogFragment")
           }
       }

       if(!add)
       {
           if (book.photo.isEmpty()) {
               ivCover.setImageResource(R.mipmap.book_cover_foreground)
               ivCover.setImageBitmap(ivCover.drawable.toBitmap().resize())
           } else {
               ivCover.setImageBitmap(book.photo.toBitmap())
           }
           etTitle.setText(book.title)
           etAuthor.setText(book.author)
           rbRating.rating = book.rating
           switchIsEl.isChecked = book.isDigital
           etComment.setText(book.comments)
       } else
       {
           if (book.photo.isEmpty()) {
               ivCover.setImageResource(R.mipmap.book_cover_foreground)
               ivCover.setImageBitmap(ivCover.drawable.toBitmap().resize())
           } else {
               ivCover.setImageBitmap(book.photo.toBitmap())
           }
           etTitle.hint=book.title
           etAuthor.hint=book.author
           rbRating.rating = book.rating
           switchIsEl.isChecked = book.isDigital
           etComment.hint=book.comments
       }

       val delete: ImageButton = root.findViewById(R.id.bt_delete)
       val save: Button =root.findViewById(R.id.bt_save4)
       val edit: ImageButton = root.findViewById(R.id.bt_edit)
       if(!add) {
           val params = save.layoutParams
           params.height = 0
           save.layoutParams = params
           etAuthor.isEnabled = false
           etComment.isEnabled = false
           etTitle.isEnabled = false
           rbRating.isEnabled = false
           switchIsEl.isEnabled=false
       }
       else
       {
           val params1=edit.layoutParams
           params1.height=0
           edit.layoutParams=params1
           val params2=delete.layoutParams
           params2.height=0
           delete.layoutParams=params2
       }

       delete.setOnClickListener {
           val realm: Realm = Realm.getDefaultInstance()
           realm.executeTransaction { realmDB ->
               val bookToDelete = realmDB.where(LibraryBook::class.java).equalTo("title", book.title).findFirst()
               bookToDelete?.deleteFromRealm()
           }
           requireActivity().onBackPressed()
       }


       edit.setOnClickListener {
           val params1=edit.layoutParams
           params1.height=0
           edit.layoutParams=params1
           val params2=delete.layoutParams
           params2.height=0
           delete.layoutParams=params2
           val params=save.layoutParams
           params.height= ActionBar.LayoutParams.WRAP_CONTENT
           save.layoutParams=params
           etAuthor.isEnabled=true
           etComment.isEnabled=true
           etTitle.isEnabled=true
           rbRating.isEnabled = true
           switchIsEl.isEnabled=true
       }

       save.setOnClickListener {
           //get data and save to realm
           book=getInfoFromFields()
           val realm: Realm = Realm.getDefaultInstance()
           realm.executeTransaction { realmDB ->
               realmDB.insertOrUpdate(book)
           }
           requireActivity().onBackPressed()
       }
       return root
    }

    private fun getInfoFromFields(): LibraryBook {
        val modifiedBook = LibraryBook()

        modifiedBook.title = etTitle.text.toString()
        modifiedBook.author = etAuthor.text.toString()
        modifiedBook.photo = ivCover.drawable.toBitmap().resize()!!.toByteArray()
        modifiedBook.rating = rbRating.rating
        modifiedBook.isDigital = switchIsEl.isChecked
        modifiedBook.comments = etComment.text.toString()

        return modifiedBook
    }

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