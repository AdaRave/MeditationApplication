package com.example.meditationapplication.adapter

import android.view.View

class RecyclerAdapter<ITEM>(
    items: List<ITEM>,
    layoutRestId: Int,
    private val bindHolder: View.(ITEM) -> Unit
) : AbstractAdapter<ITEM>(items, layoutRestId) {
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.bindHolder(itemList[position])
    }
}