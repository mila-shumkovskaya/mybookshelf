package com.study.mybookshelf.ui

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import com.study.mybookshelf.R
import com.study.mybookshelf.REQUEST_CODE_IMAGE
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.utils.toBitmap
import com.study.mybookshelf.utils.toByteArray
import io.realm.Realm
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class BorrowedBookDetailsFragment: Fragment() {

    private lateinit var ivCover: ImageView
    private lateinit var etTitle: EditText
    private lateinit var etAuthor: EditText
    private lateinit var rbRating: RatingBar
    private lateinit var switchIsEl: Switch
    private lateinit var etComment: EditText
    private lateinit var etOwner: EditText
    private lateinit var dpReceiveDate: DatePicker
    private lateinit var dpReturnDate: DatePicker

    private var receiveDate: Calendar = Calendar.getInstance()
    private var returnDate: Calendar = Calendar.getInstance()
    private var today: Calendar = Calendar.getInstance()

    lateinit var book: BorrowedBook
    private var mPath: String? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_borrowed_book_details, container, false)
        book = requireActivity().intent.getSerializableExtra("book") as BorrowedBook
        val add=requireActivity().intent.getBooleanExtra("add", false)

        ivCover = root.findViewById(R.id.iv_book_cover)
        etTitle = root.findViewById(R.id.et_title)
        etAuthor = root.findViewById(R.id.et_author)
        rbRating = root.findViewById(R.id.rating_bar)
        switchIsEl = root.findViewById(R.id.switch_is_el)
        etComment = root.findViewById(R.id.et_comment)
        etOwner = root.findViewById(R.id.et_owner)
        dpReceiveDate = root.findViewById(R.id.receive_date_picker)
        dpReturnDate = root.findViewById(R.id.return_date_picker)

        ivCover.setOnClickListener {
            if (etAuthor.isEnabled) {
                // TODO: fix or remove tempFile
                val tempFile: File = File.createTempFile("camera", ".png", requireActivity().externalCacheDir)
                mPath = tempFile.absolutePath

                val coverDialogFragment = CoverDialogFragment(tempFile, requireContext(), etTitle.text.toString(), etAuthor.text.toString())
                val manager = (context as AppCompatActivity).supportFragmentManager
                coverDialogFragment.setTargetFragment(this, REQUEST_CODE_IMAGE)
                coverDialogFragment.show(manager, "coverDialogFragment")
            }
        }

        if(!add){
            if (book.photo.isEmpty()) {
                ivCover.setImageResource(R.mipmap.book_cover)
            } else {
                ivCover.setImageBitmap(book.photo.toBitmap())
            }
            etTitle.setText(book.title)
            etAuthor.setText(book.author)
            rbRating.rating = book.rating
            switchIsEl.isChecked = book.isDigital
            etComment.setText(book.comments)
            etOwner.setText(book.owner)
        }
        else {
            if (book.photo.isEmpty()) {
                ivCover.setImageResource(R.mipmap.book_cover)
            } else {
                ivCover.setImageBitmap(book.photo.toBitmap())
            }
            etTitle.hint=book.title
            etAuthor.hint=book.author
            rbRating.rating = book.rating
            switchIsEl.isChecked = book.isDigital
            etComment.hint=book.comments
            etOwner.hint=book.owner
        }

        // create an OnDateChangedListeners
        val receiveDateChangedListener = DatePicker.OnDateChangedListener {
                _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                receiveDate.set(Calendar.YEAR, year)
                receiveDate.set(Calendar.MONTH, monthOfYear)
                receiveDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }

        val returnDateChangedListener = DatePicker.OnDateChangedListener {
                _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                returnDate.set(Calendar.YEAR, year)
                returnDate.set(Calendar.MONTH, monthOfYear)
                returnDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }

        dpReceiveDate.init(today.get(Calendar.YEAR),
                           today.get(Calendar.MONTH),
                           today.get(Calendar.DAY_OF_MONTH),
                           receiveDateChangedListener)

        dpReturnDate.init(today.get(Calendar.YEAR),
                           today.get(Calendar.MONTH),
                           today.get(Calendar.DAY_OF_MONTH),
                           returnDateChangedListener)

        val delete: ImageButton = root.findViewById(R.id.bt_delete)
        val save: Button =root.findViewById(R.id.bt_save)
        val edit: ImageButton = root.findViewById(R.id.bt_edit)
        if(!add)
        {
            val params=save.layoutParams
            params.height=0
            save.layoutParams=params
            etAuthor.isEnabled=false
            etComment.isEnabled=false
            etOwner.isEnabled=false
            etTitle.isEnabled=false
            rbRating.isEnabled = false
            switchIsEl.isEnabled=false
            dpReturnDate.isEnabled=false
            dpReceiveDate.isEnabled=false
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
                    val bookToDelete = realmDB.where(BorrowedBook::class.java).equalTo("title", book.title).findFirst()
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
            etOwner.isEnabled=true
            etTitle.isEnabled=true
            rbRating.isEnabled = true
            switchIsEl.isEnabled= true
            dpReturnDate.isEnabled=true
            dpReceiveDate.isEnabled=true
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

    private fun getInfoFromFields(): BorrowedBook {
        val modifiedBook = BorrowedBook()

        modifiedBook.title = etTitle.text.toString()
        modifiedBook.author = etAuthor.text.toString()
        modifiedBook.photo = ivCover.drawable.toBitmap().toByteArray()
        modifiedBook.rating = rbRating.rating
        modifiedBook.isDigital = switchIsEl.isChecked
        modifiedBook.comments = etComment.text.toString()

        modifiedBook.owner = etOwner.text.toString()

        val dateFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
        modifiedBook.receiveDate = sdf.format(receiveDate.time)

        modifiedBook.returnDate = sdf.format(returnDate.time)

        return modifiedBook
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("this.tag", "onActivityResult")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && (requestCode == REQUEST_CODE_IMAGE) && data != null){
            // TODO: get cover and set to ImageView
            //ivCover.setImageBitmap(data.getByteArrayExtra().toBitmap())
            /*val imageUri: Uri = data.data!!
            val chosenCover: Bitmap =  MediaStore.Images.Media.getBitmap(activity?.contentResolver, imageUri);
            Log.i("bitmap", "Cover is extracted")
            if (chosenCover != null) {
                ivCover.setImageBitmap(chosenCover!!)
                Log.i("bitmap", "Cover is selected")
            }*/
        }
    }
}