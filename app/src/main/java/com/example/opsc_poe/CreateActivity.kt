package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityCreateBinding
import com.example.opsc_poe.databinding.ActivityCreateCategoryBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CreateActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var tempImage : Bitmap

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val CAMERA_REQUEST_CODE = 200
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        imageView = findViewById(R.id.imgCamera)
        val CameraImage: Button = findViewById(R.id.btnInsertImage)

        CameraImage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else
            {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
            }

            binding.btnClick.setOnClickListener()
            {
                //create activity object
                var activities = Temp_ActivityDataClass(
                    activityID = GlobalClass.activities.size + 1,
                    userID = GlobalClass.user.userID,
                    name = binding.txtActivtyName.text.toString(),
                    description = binding.txtDescription.text.toString(),
                    photo = tempImage,
                )
                GlobalClass.activities.add(activities)
            }
        }

        }
    private fun startCamera()
    {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        } else {
            Toast.makeText(this, "Camera is not available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
            //Save the image locally
            saveImageLocally(imageBitmap)
        }
    }
    //save image locally
    private fun saveImageLocally(imageBitmap: Bitmap) {

        tempImage = imageBitmap

    }


}