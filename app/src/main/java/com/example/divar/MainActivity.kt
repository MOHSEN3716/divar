package com.example.divar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.divar.adapter.AddPlaceAdapter
import com.example.divar.database.Database

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView=findViewById<RecyclerView>(R.id.RecyclerVieaddplace)
        var databacse=Database(this)
        val list = databacse.getallusername()
        val placeAdapter = AddPlaceAdapter()
        placeAdapter.loadedState(list)
        recyclerView.adapter = placeAdapter
        recyclerView.layoutManager=LinearLayoutManager(this)

        val fab=findViewById<Button>(R.id.fabaddplus)
        fab.setOnClickListener{
            val intent = Intent(this,AddplaceActivity::class.java)
            startActivity(intent)
        }

    }
}
