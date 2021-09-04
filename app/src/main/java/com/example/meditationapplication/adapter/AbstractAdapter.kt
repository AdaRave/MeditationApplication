package com.example.meditationapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractAdapter<ITEM> constructor(
    protected var itemList: List<ITEM>,
    private val layoutResId: Int
) : RecyclerView.Adapter<AbstractAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.itemView.bind(item)
    }

    override fun getItemCount(): Int = itemList.size

    protected open fun View.bind(item: ITEM) {}
}