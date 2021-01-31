package com.study.mybookshelf.ui

import android.app.ActionBar
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LibraryBook
import io.realm.Realm

class LibraryBookDetailsFragment: Fragment(), CoverDialogFragment.OnCoverSelected {

    lateinit var ivCover: ImageView
    lateinit var etTitle: EditText
    lateinit var etAuthor: EditText
    lateinit var rbRating: RatingBar
    lateinit var switchIsEl: Switch
    lateinit var etComment: EditText

    lateinit var book: Book

   // lateinit var libraryViewModel:LibraryViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
     //   libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
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
           if (etAuthor.isEnabled == true) {
               val myDialogFragment = CoverDialogFragment(this, requireContext(), etTitle.text.toString(), etAuthor.text.toString())
               val manager = (context as AppCompatActivity).supportFragmentManager
               myDialogFragment.show(manager, "myDialog")
           }
       }

       if(!add)
       {
           ivCover.setImageResource(book.photo)
           etTitle.setText(book.title)
           etAuthor.setText(book.author)
           rbRating.rating = book.rating
           switchIsEl.isChecked = book.isDigital
           etComment.setText(book.comments)
       }else
       {
           ivCover.setImageResource(book.photo)
           etTitle.hint=book.title
           etAuthor.hint=book.author
           rbRating.rating = book.rating
           switchIsEl.isChecked = book.isDigital
           etComment.hint=book.comments
       }


       // val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
       //libraryViewModel.libraryBooksList.observe(viewLifecycleOwner, Observer {
       //    rvBooks.adapter.refreshBooks(it)
       // })
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
           book as Book
           realm.executeTransaction { realm ->

               val delbook = realm.where(LibraryBook::class.java).equalTo("title", book.title).findFirst()
               delbook?.deleteFromRealm()


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
           realm.executeTransaction { realm ->
               realm.insertOrUpdate(book)
           }
           requireActivity().onBackPressed()
       }


       return root
    }

    fun getInfoFromFields(): LibraryBook {
        val modifiedBook = LibraryBook()

        modifiedBook.title = etTitle.text.toString()
        modifiedBook.author = etAuthor.text.toString()
        //book.photo = ivCover.getDrawable() -- need conversion to byte[]
        modifiedBook.photo = this.book.photo
        modifiedBook.rating = rbRating.rating
        modifiedBook.isDigital = switchIsEl.isChecked
        modifiedBook.comments = etComment.text.toString()

        return modifiedBook
    }

    override fun selectedCover(bitmap: Bitmap) {
        Log.i("BITMAP", "bitmap selected")
        ivCover.setImageBitmap(bitmap)
    }
}