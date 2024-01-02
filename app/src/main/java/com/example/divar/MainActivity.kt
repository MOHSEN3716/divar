package com.example.divar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.divar.database.Databasehandler
import com.example.divar.database.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab=findViewById<Button>(R.id.fabaddplus)
        fab.setOnClickListener{
            val intent = Intent(this,AddplaceActivity::class.java)
            startActivity(intent)
        }

    }
}
