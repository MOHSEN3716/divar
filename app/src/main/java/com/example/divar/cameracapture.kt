package com.example.divar

import android.Manifest
import android.app.Activity
import android.app.DownloadManager.Request
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class cameracapture : AppCompatActivity() {
    lateinit var imageview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cameracapture)

        val btncamera = findViewById<Button>(R.id.btncamera)
        imageview = findViewById<ImageView>(R.id.imageView)

        val request =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    val cameraintent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraintent, 1212)
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
                }
            }

        btncamera.setOnClickListener {
            request.launch(Manifest.permission.CAMERA)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1212) {
                val bitmapImage = data?.extras?.get("data") as Bitmap
                imageview.setImageBitmap(bitmapImage)
            }
        }
    }
}
