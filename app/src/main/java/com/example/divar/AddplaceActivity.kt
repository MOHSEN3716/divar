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
import com.example.divar.database.Database
import com.example.divar.more.place
import com.google.android.material.textfield.TextInputEditText
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener
import java.io.File
import java.io.FileOutputStream
import kotlin.math.log

class AddplaceActivity : AppCompatActivity() {
    lateinit var inputdata:EditText
    lateinit var imageView:ImageView
    var imageAddress:String?=null
    var price:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addplace)

        val btnchoseimage=findViewById<Button>(R.id.btnchoseimage)
        imageView=findViewById(R.id.imageView)
        inputdata=findViewById(R.id.edtcreatedat)

        val btnaddplace=findViewById<Button>(R.id.btnaddplace)

        //اجازه دستررسی به دوربین
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

        //اجرای تاریخ
        inputdata.setOnClickListener{
            showdatapicker()
        }

        val edttitle=findViewById<EditText>(R.id.edttitle)
        val edtprice=findViewById<EditText>(R.id.edtprice)
        val edtmetr=findViewById<EditText>(R.id.edtmetr)
        val edtyear=findViewById<EditText>(R.id.edtyear)


          btnaddplace.setOnClickListener{

            val Title = edttitle.text.toString()
            val priceString = edtprice?.text?.toString()
            val price = priceString?.toIntOrNull() ?: 0
            val imageaddress = imageAddress?.toInt() ?: 0
            val year = edtyear.text.toString()
            val Createdat=inputdata.text.toString()
            val metr = edtmetr.text.toString().toInt()
            val place = place(
                Title,
                price!!,
                imageaddress!!,
                metr,
                Createdat,
                year
            )
            val Database=Database(this)
              Log.d("TAGXXX","onResponse")
            Database.addplace(place)
              Toast.makeText(this, "شد.", Toast.LENGTH_SHORT).show()
          }

    }
    //تاریخ
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

    //عکس و زخیره
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1212) {
            val bitmapImage = data?.extras?.get("data") as Bitmap
            val imageAddress=savebitmapeimage(bitmapImage)
            imageView.setImageBitmap(bitmapImage)
        }
    }
    fun savebitmapeimage(bitmap: Bitmap):String{
        val file =bildfile("${System.currentTimeMillis()}.jpg")
        val portal=biledoutpuotstreem(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,portal)
        portal.flush()
        portal.close()
        return file.absolutePath
        Log.d("TAGXXX","onResponseeee")

    }
    fun biledoutpuotstreem(file: File): FileOutputStream {
        return FileOutputStream(file)
    }
    fun bildfile(filename:String): File {
        return File(this.filesDir,filename)
    }

}