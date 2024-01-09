package com.example.divar

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener
import java.io.File
import java.io.FileOutputStream

class AddplaceActivity : AppCompatActivity() {
    lateinit var inputdata:EditText
    lateinit var imageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addplace)
        val btnchoseimage=findViewById<Button>(R.id.btnchoseimage)
        imageView=findViewById(R.id.imageView)

        val request =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    val cameraintent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraintent, 1212)
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
                }
            }

        btnchoseimage.setOnClickListener {
            request.launch(Manifest.permission.CAMERA)
        }

        inputdata=findViewById(R.id.edtcreatedat)
        inputdata.setOnClickListener{
            showdatapicker()
        }


    }
    fun showdatapicker(){
        var picker = PersianDatePickerDialog(this)
            .setPositiveButtonString("رواله")
            .setNegativeButton("بیخیل")
            .setTodayButton("الان")
            .setTodayButtonVisible(true)
            .setMinYear(1300)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setMaxMonth(PersianDatePickerDialog.THIS_MONTH)
            .setMaxDay(PersianDatePickerDialog.THIS_DAY)
            .setInitDate(1370, 3, 13)
            .setActionTextColor(Color.GRAY)
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setShowInBottomSheet(true)
            .setListener(object : PersianPickerListener {
                override fun onDateSelected(persianPickerDate: PersianPickerDate) {
                    inputdata.setText(persianPickerDate.persianLongDate)

                }
                override fun onDismissed() {}
            })

        picker.show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1212) {
            val bitmapImage = data?.extras?.get("data") as Bitmap
            val address=savebitmapeimage(bitmapImage)
            imageView.setImageBitmap(bitmapImage)
            Log.d("TAGX","onActivityResult:${address}")
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
    fun biledoutpuotstreem(file: File): FileOutputStream {
        return FileOutputStream(file)
    }
    fun bildfile(filename:String): File {
        return File(this.filesDir,filename)
    }

}