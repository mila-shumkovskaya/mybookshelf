package com.study.mybookshelf.ui


import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.DialogFragment
import com.study.mybookshelf.R
import kotlinx.android.synthetic.main.nav_header_main.*

class CoverDialogFragment(context: Context) : DialogFragment() {

    private val items = arrayOf(context.getString(R.string.from_camera), context.getString(R.string.from_gallery), context.getString(R.string.from_internet))
    private val REQUEST_CODE_CAMERA = 200
    private val REQUEST_CODE_GALLERY = 100
    private val PERMISSION_CODE_СAMERA = 1000
    private val PERMISSION_CODE_GALLERY = 1001

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
            requestPermissions(permission, PERMISSION_CODE_СAMERA)
        } else{
            openCamera()
        }
    }

    private fun getFromGallery() {
        if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            //permission was not enabled
            val permission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            //show popup to request permission
            requestPermissions(permission, PERMISSION_CODE_GALLERY)
        } else{
            openGallery()
        }
    }

    private fun getFromInternet() {
        Toast.makeText(activity, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when(requestCode){
            REQUEST_CODE_CAMERA -> {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && (requestCode == REQUEST_CODE_CAMERA || requestCode == REQUEST_CODE_GALLERY) && data != null){
            imageView.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }

}