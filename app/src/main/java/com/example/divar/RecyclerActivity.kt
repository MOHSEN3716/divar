package com.example.divar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.divar.adapter.itemAdapter
import com.example.divar.database.Databasehandler

class RecyclerActivity : AppCompatActivity(),ItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler2)

        val RecyclerView=findViewById<RecyclerView>(R.id.RV)
        val itemAdapter=itemAdapter(this)
        RecyclerView.adapter= itemAdapter
        RecyclerView.layoutManager=LinearLayoutManager(this)


        val Databasehandler=Databasehandler(this)
        val usernameArray= Databasehandler.getallusername()

        itemAdapter.loadedState(usernameArray)
    }

    override fun onitemclick(username:String) {
        Toast.makeText(this, username, Toast.LENGTH_LONG).show()
    }
}