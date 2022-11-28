package com.fynn.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplebuildpro.R

class MainViewHolder(parent:ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_main_recycler, parent, false)
) {
    private var text: TextView = itemView.findViewById(R.id.item_text)


    fun bind(str: String){
        text.text = str
    }
}