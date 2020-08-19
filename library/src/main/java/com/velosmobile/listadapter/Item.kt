package com.velosmobile.listadapter

import android.view.View
import androidx.annotation.LayoutRes

interface Item {
    val content: ItemContent
    @get:LayoutRes val layout: Int
    fun bind(view: View)

    /**
     * Optional method to clean up resources (such as large bitmaps) when the view gets recycled.
     */
    fun destroy(view: View) { }
}