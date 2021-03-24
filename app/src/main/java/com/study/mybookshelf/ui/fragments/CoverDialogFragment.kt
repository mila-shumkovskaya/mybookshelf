package com.study.mybookshelf.ui.fragments

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.study.mybookshelf.R
import com.study.mybookshelf.REQUEST_CODE_CAMERA
import com.study.mybookshelf.REQUEST_CODE_GALLERY
import com.study.mybookshelf.REQUEST_CODE_INTERNET
import com.study.mybookshelf.google_books_api.GetCoverClass
import com.study.mybookshelf.google_books_api.ImageListAdapter
import com.study.mybookshelf.utils.resize
import com.study.mybookshelf.utils.toByteArray
import java.io.IOException


const val PERMISSION_CODE_CAMERA = 1001
const val PERMISSION_CODE_GALLERY = 1002

class CoverDialogFragment(context: Context, val title: String, val author: String) : DialogFragment() {

    private val items = arrayOf(context.getString(R.string.from_camera), context.getString(R.string.from_gallery), context.getString(R.string.from_internet))

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.load_cover)
                .setItems(items) { _, which ->
                    when (which) {
                        0 -> getFromCamera()
                        1 -> getFromGallery()
                        2 -> getFromInternet()
                    }

                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    // check access to camera
    private fun getFromCamera() {
        if (checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            val permission = arrayOf(Manifest.permission.CAMERA)
            //show popup to request permission
            requestPermissions(permission, PERMISSION_CODE_CAMERA)
        } else {
            openCamera()
        }
    }

    // check access to gallery
    private fun getFromGallery() {
        if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            //show popup to request permission
            requestPermissions(permission, PERMISSION_CODE_GALLERY)
        } else {
            openGallery()
        }
    }

    // retrieve books' covers
    private fun getFromInternet() {
        if (isOnline()) {
            Log.i(this.tag, "Online")
            val getCoverClass = GetCoverClass(requireActivity())
            getCoverClass.getCover(title, author, ::openInternetCoversDialog)
        }
        else {
            Log.i(this.tag, "Offline")
            Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
        }

    }

    //called when user presses ALLOW or DENY from Permission Request Popup
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else { Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show() }
            }
            PERMISSION_CODE_GALLERY -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else { Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show() }
            }
            else -> { Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show() }
        }
    }

    // check the Internet connection
    private fun isOnline(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return false
    }


    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            activity?.startActivityForResult(intent, REQUEST_CODE_CAMERA)
            Log.i(this.tag, "OpenCamera")
        } catch (e: ActivityNotFoundException) {
            Log.i(this.tag, "Cannot open Camera")
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        try {
            activity?.startActivityForResult(intent, REQUEST_CODE_GALLERY)
            Log.i(this.tag, "OpenGallery")
        } catch (e: ActivityNotFoundException) {
            Log.i(this.tag, "Cannot open Gallery")
        }
    }

    // show dialog to choose cover for book from Internet
    private fun openInternetCoversDialog(activity: Activity, imageList: ArrayList<Bitmap>): Unit {
        if (imageList.isNullOrEmpty()) {
            Toast.makeText(activity.baseContext, activity.getString(R.string.no_books_found), Toast.LENGTH_SHORT).show()
        } else {
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val view: View = inflater.inflate(R.layout.dialog_internet_covers, null)
            builder.setTitle(R.string.choose_cover)
            val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
            // set adapter and onClickListener
            val imageListAdapter = ImageListAdapter(imageList) { byteArray: ByteArray ->
                val intent = Intent()
                intent.putExtra("image", byteArray)
                targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            }
            recyclerView.adapter = imageListAdapter
            builder.setView(view)
            builder.setCancelable(true)
            builder.setNegativeButton(R.string.close) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
            }
            builder.show()
        }
    }

}