package com.study.mybookshelf.ui


import android.Manifest
import android.R.attr.thumbnail
import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.study.mybookshelf.R
import com.study.mybookshelf.google_books_api.GetCoverClass
import java.io.ByteArrayOutputStream
import java.io.IOException


class CoverDialogFragment(private val onCoverSelected: OnCoverSelected, context: Context, val title: String, val author: String) : DialogFragment() {

    private val items = arrayOf(context.getString(R.string.from_camera), context.getString(R.string.from_gallery), context.getString(R.string.from_internet))
    private var bitmapList: ArrayList<Bitmap> = arrayListOf()
    private var chosenCover: Bitmap? = null
    private val REQUEST_CODE_CAMERA = 100
    private val REQUEST_CODE_GALLERY = 200
    //private val REQUEST_CODE_INTERNET = 300
    private val PERMISSION_CODE_CAMERA = 1001
    private val PERMISSION_CODE_GALLERY = 1002
    //private val PERMISSION_CODE_INTERNET = 1003

    private val coverWidth = 192
    private val coverHeight = 192

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

    private fun getFromCamera() {
        if (checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            //permission was not enabled
            val permission = arrayOf(Manifest.permission.CAMERA)
            //show popup to request permission
            requestPermissions(permission, PERMISSION_CODE_CAMERA)
        } else{
            openCamera()
        }
    }

    private fun getFromGallery() {
        if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            //permission was not enabled
            val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            //show popup to request permission
            requestPermissions(permission, PERMISSION_CODE_GALLERY)
        } else{
            openGallery()
        }
    }

    private fun getFromInternet() {
        /*if (checkSelfPermission(requireContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED
            || checkSelfPermission(requireContext(), Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_DENIED){
            //permission was not enabled
            val permission = arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)
            //show popup to request permission
            requestPermissions(permission, PERMISSION_CODE_INTERNET)
        } else{
            openInternetCoversDialog()
        }*/
        openInternetCoversDialog()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try { startActivityForResult(intent, REQUEST_CODE_CAMERA) } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        try { startActivityForResult(intent, REQUEST_CODE_GALLERY) } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    private fun openInternetCoversDialog() {
        if (isOnline()) {
            val getCoverClass = GetCoverClass()
            getCoverClass.getCover(title, author)
            getCoverClass.livaDataJsonBooks.observe(this, Observer {
                bitmapList = it.getBitmapArrayList()
            })
        }
        else {
            Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
        }
    }

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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.i("bitmap", "onRequestPermissionsResult")
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            PERMISSION_CODE_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("bitmap", "openCamera")
                    openCamera()
                } else { Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show() }
            }
            PERMISSION_CODE_GALLERY -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("bitmap", "openGallery")
                    openGallery()
                } else { Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show() }
            }
            /*REQUEST_CODE_INTERNET -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openInternetCoversDialog()
                } else { Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show() }
            }*/
            else -> { Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show() }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("bitmap", "onActivityresult")
        if (resultCode == Activity.RESULT_OK && (requestCode == REQUEST_CODE_CAMERA || requestCode == REQUEST_CODE_GALLERY) && data != null){
            chosenCover = data.extras?.get("data") as Bitmap
            Log.i("bitmap", "Cover is extracted")
            if (chosenCover != null) {
                onCoverSelected.selectedCover(chosenCover!!)
                Log.i("bitmap", "Cover is selected")
            }
            dialog?.dismiss()
        }
    }

    interface OnCoverSelected{
        fun selectedCover(bitmap: Bitmap)
    }
}

