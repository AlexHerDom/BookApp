package com.mylibrary.bookapp.presentation.ui.view.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mylibrary.bookapp.presentation.ui.viewmodel.EventViewModel
import com.mylibrary.core.domain.Data
import com.mylibrary.core.domain.EventDescriptionResponse
import dagger.hilt.android.AndroidEntryPoint
import mylibrary.bookapp.R
import mylibrary.bookapp.databinding.FragmentCreateEventBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class CreateEventFragment : Fragment() {

    private lateinit var binding: FragmentCreateEventBinding
    private val eventViewModel: EventViewModel by viewModels()

    private var permissionLauncher: ActivityResultLauncher<Array<String>>? = null
    private var isCameraPermissionGranted = false
    private var isReadPermissionGranted = false
    private var isWritePermissionGranted = false

    private val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath: String
    private var photoFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                isCameraPermissionGranted =
                    permissions[Manifest.permission.CAMERA] ?: isCameraPermissionGranted
                isReadPermissionGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE]
                    ?: isReadPermissionGranted
                isWritePermissionGranted = permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE]
                    ?: isWritePermissionGranted
            }

        requestPermission()

        binding.cvCamera.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.fabAddEvent.setOnClickListener {
            binding.apply {
                eventViewModel.saveEvent(
                    EventDescriptionResponse(
                        Data(
                            avatar = photoFile.toString(),
                            email = tvContact.editText?.text.toString(),
                            first_name = tvName.editText?.text.toString(),
                            (50..500).random(),
                            last_name = ""
                        ),
                        null
                    )
                )
            }

            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage(R.string.event_saved)
                .setPositiveButton(
                    R.string.accept
                ) { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create().show()
        }
    }

    private fun requestPermission() {
        isCameraPermissionGranted = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        isReadPermissionGranted = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        isWritePermissionGranted = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val permissionRequest: MutableList<String> = ArrayList()

        if (!isCameraPermissionGranted)
            permissionRequest.add(Manifest.permission.CAMERA)

        if (!isReadPermissionGranted)
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)

        if (!isWritePermissionGranted)
            permissionRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (permissionRequest.isNotEmpty())
            permissionLauncher?.launch(permissionRequest.toTypedArray())
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            try {
                photoFile = createImageFile()
                if (photoFile != null) {
                    val photoURI = FileProvider.getUriForFile(
                        requireContext(),
                        "com.mylibrary.bookapp",
                        photoFile!!
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        } else {
            Toast.makeText(
                requireContext(),
                "Null",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val myBitmap = BitmapFactory.decodeFile(photoFile!!.absolutePath)
            binding.ivEvent.setImageBitmap(myBitmap)
        } else {
            Toast.makeText(
                requireContext(),
                "Request cancelled or something went wrong.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? =
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }
}