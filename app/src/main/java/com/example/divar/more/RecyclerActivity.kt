package com.example.divar.more

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.divar.R
import com.example.divar.adapter.itemAdapter
import com.example.divar.database.Database

class RecyclerActivity : AppCompatActivity(), ItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler2)

        val RecyclerView=findViewById<RecyclerView>(R.id.RV)
        val itemAdapter=itemAdapter(this)
        RecyclerView.adapter= itemAdapter
        RecyclerView.layoutManager=LinearLayoutManager(this)


        val Database=Database(this)
//        val usernameArray= Database.getallusername()

//        itemAdapter.loadedState(usernameArray)
    }

    override fun onitemclick(username:String) {
        Toast.makeText(this, username, Toast.LENGTH_LONG).show()
    }
}