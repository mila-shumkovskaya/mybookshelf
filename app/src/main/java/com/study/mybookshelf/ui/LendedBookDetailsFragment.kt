package com.study.mybookshelf.ui

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

class LendedBookDetailsFragment: Fragment() {

    lateinit var ivCover: ImageView
    lateinit var etTitle: EditText
    lateinit var etAuthor: EditText
    lateinit var rbRating: RatingBar
    lateinit var switchIsEl: Switch
    lateinit var etComment: EditText
    lateinit var etRecipient: EditText
    lateinit var dpTransferDate: DatePicker
    lateinit var dpReturnDate: DatePicker

    var transferDate = Calendar.getInstance()
    var returnDate = Calendar.getInstance()
    var today = Calendar.getInstance()

    lateinit var book: LendedBook

    // lateinit var libraryViewModel:LibraryViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //   libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lended_book_details, container, false)
        book = requireActivity().intent.getSerializableExtra("book") as LendedBook
        val add=requireActivity().intent.getBooleanExtra("add", false)

        ivCover = root.findViewById(R.id.iv_book_cover)
        etTitle = root.findViewById(R.id.et_title)
        etAuthor = root.findViewById(R.id.et_author)
        rbRating = root.findViewById(R.id.rating_bar)
        switchIsEl = root.findViewById(R.id.switch_is_el)
        etComment = root.findViewById(R.id.et_comment)
        etRecipient = root.findViewById(R.id.et_recipient)
        dpTransferDate = root.findViewById(R.id.transfer_date_picker)
        dpReturnDate = root.findViewById(R.id.return_date_picker)

        ivCover.setOnClickListener {
            if (etAuthor.isEnabled == true) {
                val myDialogFragment = CoverDialogFragment(requireContext())
                val manager = (context as AppCompatActivity).supportFragmentManager
                myDialogFragment.show(manager, "myDialog")
            }
        }

        if(!add){
            ivCover.setImageResource(book.photo)
            etTitle.setText(book.title)
            etAuthor.setText(book.author)
            rbRating.rating = book.rating
            switchIsEl.isChecked = book.isDigital
            etComment.setText(book.comments)
            etRecipient.setText(book.recipient)
        }
        else
        {
            ivCover.setImageResource(book.photo)
            etTitle.hint=book.title
            etAuthor.hint=book.author
            rbRating.rating = book.rating
            switchIsEl.isChecked = book.isDigital
            etComment.hint=book.comments
            etRecipient.hint=book.recipient
        }


        // val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
        //libraryViewModel.libraryBooksList.observe(viewLifecycleOwner, Observer {
        //    rvBooks.adapter.refreshBooks(it)
        // })

        // create an OnDateChangedListeners
        val transferDateChangedListener = DatePicker.OnDateChangedListener {
                _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            transferDate.set(Calendar.YEAR, year)
            transferDate.set(Calendar.MONTH, monthOfYear)
            transferDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        val returnDateChangedListener = DatePicker.OnDateChangedListener {
                _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            returnDate.set(Calendar.YEAR, year)
            returnDate.set(Calendar.MONTH, monthOfYear)
            returnDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        dpTransferDate.init(today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH),
            transferDateChangedListener)

        dpReturnDate.init(today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH),
            returnDateChangedListener)

        val delete: ImageButton = root.findViewById(R.id.bt_delete)
        val save: Button =root.findViewById(R.id.bt_save2)
        val edit: ImageButton = root.findViewById(R.id.bt_edit)
        if(!add)
        {
            val params=save.layoutParams
            params.height=0
            save.layoutParams=params
            etAuthor.isEnabled=false
            etComment.isEnabled=false
            etRecipient.isEnabled=false
            etTitle.isEnabled=false
            rbRating.isEnabled = false
            switchIsEl.isEnabled=false
            dpReturnDate.isEnabled=false
            dpTransferDate.isEnabled=false
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
            val myDialogFragment = DeleteDialogFragment(book)
            val manager = (context as AppCompatActivity).supportFragmentManager
            myDialogFragment.show(manager, "myDialog")
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
            etRecipient.isEnabled=true
            etTitle.isEnabled=true
            rbRating.isEnabled = true
            switchIsEl.isEnabled=true
            dpReturnDate.isEnabled=true
            dpTransferDate.isEnabled=true
        }

        save.setOnClickListener {
            //get data and save to realm
            val id=book.id
            book=getInfoFromFields()
            book.id=id
            if(add)
            {
                SharedPreferencesId(requireContext()).saveId(id)
            }
            val realm: Realm = Realm.getDefaultInstance()
            realm.executeTransaction { realm ->
                realm.insertOrUpdate(book)
            }
            requireActivity().onBackPressed()
        }


        return root
    }

    fun getInfoFromFields(): LendedBook {
        val modifiedBook = LendedBook()

        modifiedBook.title = etTitle.text.toString()
        modifiedBook.author = etAuthor.text.toString()
        //book.photo = ivCover.getDrawable() -- need conversion to byte[]
        modifiedBook.photo = this.book.photo
        modifiedBook.rating = rbRating.rating
        modifiedBook.isDigital = switchIsEl.isChecked
        modifiedBook.comments = etComment.text.toString()

        modifiedBook.recipient = etRecipient.text.toString()

        val dateFormat = "yyyy-mm-dd"
        val sdf = SimpleDateFormat(dateFormat, Locale.US)
        modifiedBook.transferDate = sdf.format(transferDate.time)

        modifiedBook.returnDate = sdf.format(returnDate.time)

        return modifiedBook
    }
}