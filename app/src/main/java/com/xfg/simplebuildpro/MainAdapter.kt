package com.fynn.myapplication

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(list: MutableList<String>): RecyclerView.Adapter<MainViewHolder>() {

    private val list = ArrayList<String>()

    private var listener: OnItemClickListener? = null

    init {
        this.list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
        if (listener != null){
            holder.itemView.setOnClickListener {
                listener?.onItemClickListener(list[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        listener = onItemClickListener
    }

    interface OnItemClickListener{
        fun onItemClickListener(str: String, position: Int)
    }
}