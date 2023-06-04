package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.example.opsc_poe.databinding.ActivityCreateBinding
import com.google.common.util.concurrent.ListenableFuture
import java.io.File
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var cameraSelector: CameraSelector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val cameraProviderResult = registerForActivityResult(
          //  ActivityResultContract.RequestPermission()) { permissionGranted->
            //if (permissionGranted) {
             //   startCamera()
           // } else {
                Toast.makeText(this@CreateActivity, "Cannot take photos without permission", Toast.LENGTH_SHORT)
            }

        }
        // Set click listener for the "Insert Image" button
       // val btnInsertImage: Button = findViewById(R.id.btnInsertImage)
        //btnInsertImage.setOnClickListener {
            //cameraProviderResult.launch(android.Manifest.permission.CAMERA)
        //}


    //}

    private fun startCamera()
    {

      //  cameraProviderFuture = ProcessCameraProvider.getInstance(this)
     //   cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
      //  cameraProviderFuture.addListener(
            {

                    // Get the camera provider
                   // val cameraProvider = cameraProviderFuture.get()

                    // Set up the preview use case1
                   // val preview = Preview.Builder().build().also { it.setSurfaceProvider(binding.imgTurtle.surfaceProvider) }

                    try {
                      //  cameraProvider.unbindAll()
                     //   cameraProvider.bindToLifecycle(this,cameraSelector,preview)
                    } catch (e: Exception)
                    {
                    Log.d("Create Activity", "Use case binding failed")
                    }

           // },ContextCompat.getMainExecutor(this))


    }


}