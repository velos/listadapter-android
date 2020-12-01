package com.velosmobile.listadapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

interface Item {
    val content: ItemContent
    val viewBinding: ViewBinding
    fun bind(view: View)

    /**
     * Optional method to clean up resources (such as large bitmaps) when the view gets recycled.
     */
    fun destroy(view: View) { }
}