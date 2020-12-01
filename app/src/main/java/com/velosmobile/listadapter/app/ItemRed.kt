package com.velosmobile.listadapter.app

import android.view.View
import com.velosmobile.listadapter.Item
import com.velosmobile.listadapter.R
import com.velosmobile.listadapter.app.viewmodel.ItemContentRed

class ItemRed(
    override val content: ItemContentRed,
    val onClick: (ItemContentRed) -> Unit
) : Item {
    override val layout: Int = R.layout.item_red

    override fun bind(view: View) {
        view.text.text = content.text
        view.setOnClickListener { onClick.invoke(content) }
    }
}