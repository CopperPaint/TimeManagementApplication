package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import java.io.File
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CreateActivity : AppCompatActivity() {

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var previewView: PreviewView
    private lateinit var imageCapture: ImageCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        // Initialize the ExecutorService for camera operations
        cameraExecutor = Executors.newSingleThreadExecutor()

        // Initialize the PreviewView
        previewView = findViewById(R.id.previewView)

        // Set click listener for the "Insert Image" button
        val btnInsertImage: Button = findViewById(R.id.btnInsertImage)
        btnInsertImage.setOnClickListener {


            captureImage()
        }

        startCamera()
    }

    private fun startCamera()
    {
        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(
            {
                try {
                    // Get the camera provider
                    val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                    // Set up the preview use case
                    val preview = Preview.Builder().build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)

                    // Set up the image capture use case
                    imageCapture = ImageCapture.Builder().build()

                    // Select back camera as the default
                    val cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    // Unbind any previously bound use cases
                    cameraProvider.unbindAll()

                    // Bind the use cases to the camera
                    val camera: Camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)

                } catch (e: ExecutionException) {
                    // Handle exceptions
                } catch (e: InterruptedException) {
                    // Handle exceptions
                }
            },
            ContextCompat.getMainExecutor(this)
        )
    }
    private fun captureImage() {
        val imageCapture = imageCapture ?: return

        // Create a file to save the image
        val photoFile = File(externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")

        // Set up the output options object
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Take a picture
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // Image saved successfully, do something with the saved image file
                }

                override fun onError(exception: ImageCaptureException) {
                    // Handle errors
                }
            }
        )
    }

}