package com.example.divar.adapter

import android.content.Context
import android.widget.TextView
import com.example.divar.R
import com.example.divar.more.ItemClickListener
import com.example.divar.more.place
import io.github.farshidroohi.AdapterRecyclerView

class AddPlaceAdapter:AdapterRecyclerView<place>(R.layout.item_recycleraddapter,0,0,0) {

    override fun onBindView(
        viewHolder: ItemViewHolder,
        position: Int,
        context: Context,
        element: place?
    ) {
        val view=viewHolder.itemView
        val textTitle=view.findViewById<TextView>(R.id.textTitleAddplace)

        textTitle.text= element?.title
    }


}