package com.example.divar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.divar.database.Databasehandler
import com.example.divar.database.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val databasehandler=Databasehandler(this)
        val edt= findViewById<EditText>(R.id.editTextText)
        val btnadd = findViewById<Button>(R.id.btnadd)
        val btndelete = findViewById<Button>(R.id.btndelete)
        val btnupdate = findViewById<Button>(R.id.btnupdate)


        val text = edt.text.toString()
        btnadd.setOnClickListener{
            val username= edt.text.toString()
            val user = User(username)
            databasehandler.addUser(user)
        }

        btndelete.setOnClickListener{
            databasehandler.deleteUserr("5")
        }
        btnupdate.setOnClickListener{
            val user=User("Ali")
            databasehandler.updateuser("5",user)


        }




    }
}
