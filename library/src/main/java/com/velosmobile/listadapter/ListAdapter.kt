package com.velosmobile.listadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(differ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).layout
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position).bind(holder.itemView)
    }

    private class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

interface Item {
    val content: ItemContent
    @get:LayoutRes val layout: Int
    fun bind(view: View)
}

interface ItemContent {
    val id: Any
    override fun equals(other: Any?): Boolean
}

private val differ = object : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.content.id == newItem.content.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.content == newItem.content
    }
}