package com.velosmobile.listadapter

import androidx.recyclerview.widget.DiffUtil

internal class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.content.id == newItem.content.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.content == newItem.content
    }
}