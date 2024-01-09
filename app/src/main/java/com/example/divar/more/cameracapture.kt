package com.example.divar.more

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.divar.R
import java.io.File
import java.io.FileOutputStream

class cameracapture : AppCompatActivity() {
    lateinit var imageview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cameracapture)

        val btncamera = findViewById<Button>(R.id.btncamera)
        imageview = findViewById(R.id.imageView)

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
                savebitmapeimage(bitmapImage)
            }
        }
    }

    fun savebitmapeimage(bitmap: Bitmap):String{
        val file =bildfile("${System.currentTimeMillis()}.jpg")
        val portal=biledoutpuotstreem(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,portal)
        portal.flush()
        portal.close()
        return file.absolutePath
    }
    fun biledoutpuotstreem(file:File):FileOutputStream{
        return FileOutputStream(file)
    }
    fun bildfile(filename:String):File {
        return File(this.filesDir,filename)
    }
}
