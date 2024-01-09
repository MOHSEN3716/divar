package com.example.divar.adapter

import android.content.Context
import android.widget.TextView
import com.example.divar.more.ItemClickListener
import com.example.divar.R
import io.github.farshidroohi.AdapterRecyclerView

class itemAdapter(val itemClickListener: ItemClickListener):AdapterRecyclerView<String>(R.layout.item_recyclerview,0,0,0) {
    override fun onBindView(
        viewHolder: ItemViewHolder,
        position: Int,
        context: Context,
        element: String?
    ) {
        val view=viewHolder.itemView
        val textTitle=view.findViewById<TextView>(R.id.textTitle)
        view.setOnClickListener{
            itemClickListener.onitemclick(element!!)




        }
        textTitle.text= element

    }
}