package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.Manifest
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.widget.*
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

        //Spinner
        //----------------------------------------------------------------------------------
        val items = arrayListOf<String>()
        val indexes = arrayListOf<Int>()
        for (i in GlobalClass.categories.indices)
        {
            //if category belongs to user
            if (GlobalClass.categories[i].userID == GlobalClass.user.userID)
            {
                items.add(GlobalClass.categories[i].name)
                indexes.add(i)
            }
        }
        val spinner = findViewById<Spinner>(R.id.spCategory)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, items)
            spinner.adapter = adapter
        }

        imageView = findViewById(R.id.imgCamera)
        val CameraImage: Button = findViewById(R.id.btnInsertImage)

        CameraImage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else
            {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
            }
        }

        binding.btnClick.setOnClickListener()
        {
            //code to get selected category
            var selectedItem = spinner.selectedItemPosition
            var category = GlobalClass.categories[indexes[selectedItem]]

            //create activity object
            var activities = Temp_ActivityDataClass(
                activityID = GlobalClass.activities.size + 1,
                userID = GlobalClass.user.userID,
                categoryID = category.categoryID, //get current category ID
                name =  binding.etActivtyName.text.toString(),
                description = binding.etDescription.text.toString(),
                maxgoalID = GlobalClass.goals.size + 1,
                mingoalID = GlobalClass.goals.size + 2,
                photo = tempImage
            )
            //save activity
            GlobalClass.activities.add(activities)

            //create max activity goal
            var maxgoal = Temp_GoalDataClass(
                goalID = GlobalClass.goals.size + 1,
                userID = GlobalClass.user.userID,
            )
            var mingoal = Temp_GoalDataClass(
                goalID = GlobalClass.goals.size + 2,
                userID = GlobalClass.user.userID,
            )
            GlobalClass.goals.add(maxgoal)
            GlobalClass.goals.add(mingoal)


            /*
            //--------------------------------------------------------------------------------------------
            //copy bitmap as a string for testing
            val clipboard = ContextCompat.getSystemService(this, ClipboardManager::class.java)
            clipboard?.setPrimaryClip(ClipData.newPlainText("",tempImage.toString()))
            //--------------------------------------------------------------------------------------------
             */
            //return user to the home view screen
            var intent = Intent(this, Home_Activity::class.java)
            startActivity(intent)


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