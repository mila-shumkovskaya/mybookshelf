package com.study.mybookshelf.ui


import android.Manifest
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
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.study.mybookshelf.R
import com.study.mybookshelf.REQUEST_CODE_IMAGE
import com.study.mybookshelf.google_books_api.GetCoverClass
import io.realm.Realm.getApplicationContext
import java.io.File
import java.io.IOException

const val PERMISSION_CODE_CAMERA = 1001
const val PERMISSION_CODE_GALLERY = 1002

class CoverDialogFragment(var tempFile: File, context: Context, val title: String, val author: String) : DialogFragment() {

    private val items = arrayOf(context.getString(R.string.from_camera), context.getString(R.string.from_gallery), context.getString(R.string.from_internet))
    private var bitmapList: ArrayList<Bitmap> = arrayListOf()

    private val onActivityResultReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.i("bitmap", "onActivityResult")
            val chosenCover: ByteArray? = intent.getByteArrayExtra("chosen_cover")
            if (chosenCover != null) {
                onCoverSelected.selectedCover(chosenCover!!.toBitmap())
                Log.i("bitmap", "Cover is selected")
            }
            dialog?.dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            context?.let { it1 ->
                LocalBroadcastManager.getInstance(it1).registerReceiver(onActivityResultReceiver,
                    IntentFilter("onActivityResult")
                )
            };
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

    // retrieve books' covers
    private fun getFromInternet() {
        if (isOnline()) {
            val getCoverClass = GetCoverClass()
            getCoverClass.getCover(title, author)
            getCoverClass.livaDataJsonBooks.observe(this, Observer {
                bitmapList = it.getBitmapArrayList()
            })
            //openInternetCoversDialog(bitmapList)
        }
        else {
            Toast.makeText(requireContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
        }

    }

    private fun openCamera() {
        // Create a temp file to store the image and store path in a member variable
        val uri = getApplicationContext()?.let { FileProvider.getUriForFile(it, requireActivity().packageName + ".provider", tempFile) }

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.setAction(Intent.ACTION_GET_CONTENT);
        try {
            startActivityForResult(intent, REQUEST_CODE_CAMERA)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    private fun openGallery() {
        val uri = getApplicationContext()?.let { FileProvider.getUriForFile(it, requireActivity().packageName + ".provider", tempFile) }

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        try {
            startActivityForResult(intent, REQUEST_CODE_GALLERY)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    private fun openInternetCoversDialog() {
        // TODO: create dialog to choose image
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

}

