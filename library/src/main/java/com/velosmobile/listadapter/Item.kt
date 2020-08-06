package com.velosmobile.listadapter

import android.view.View
import androidx.annotation.LayoutRes

interface Item {
    val content: ItemContent
    @get:LayoutRes val layout: Int
    fun bind(view: View)
}