package com.velosmobile.listadapter.app

import android.view.View
import com.velosmobile.listadapter.Item
import com.velosmobile.listadapter.R
import com.velosmobile.listadapter.app.viewmodel.ItemContentBlue

class ItemBlue(
    override val content: ItemContentBlue,
    val onClick: (ItemContentBlue) -> Unit
) : Item {
    override val layout: Int = R.layout.item_blue

    override fun bind(view: View) {
        view.text.text = content.text
        view.setOnClickListener { onClick.invoke(content) }
    }
}