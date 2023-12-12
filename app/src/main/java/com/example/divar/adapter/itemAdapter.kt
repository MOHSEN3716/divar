package com.example.divar.adapter

import android.content.Context
import android.widget.TextView
import com.example.divar.R
import com.example.divar.database.User
import io.github.farshidroohi.AdapterRecyclerView

class itemAdapter():AdapterRecyclerView<String>(R.layout.item_recyclerview,0,0,0) {
    override fun onBindView(
        viewHolder: ItemViewHolder,
        position: Int,
        context: Context,
        element: String?
    ) {
        val view=viewHolder.itemView
        val textTitle=view.findViewById<TextView>(R.id.textTitle)
        textTitle.text= element

    }
}