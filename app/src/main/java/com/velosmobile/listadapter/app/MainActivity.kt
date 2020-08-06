package com.velosmobile.listadapter.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.velosmobile.listadapter.Item
import com.velosmobile.listadapter.ItemContent
import com.velosmobile.listadapter.ListAdapter
import com.velosmobile.listadapter.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_type_1.view.*

class MainActivity : AppCompatActivity() {
    private val adapter = ListAdapter()
    private val onClickListener: (MainItemContent) -> Unit = { content ->
        Toast.makeText(this, content.text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        refresh_button.setOnClickListener { refresh() }
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        refresh()
    }

    private fun refresh() {
        val data = mutableListOf<Item>()

        for (i in 0..10) {
            if (i % 2 == 0) {
                data.add(
                    ItemType1(
                        content = MainItemContent(id = i, text = i.toString()),
                        onClick = onClickListener)
                )
            } else {
                data.add(
                    ItemType2(
                        content = MainItemContent(id = i, text = i.toString()),
                        onClick = onClickListener)
                )
            }
        }

        adapter.submitList(data.apply { shuffle() })
    }

    class ItemType1(
        override val content: MainItemContent,
        val onClick: (MainItemContent) -> Unit
    ) : Item {
        override val layout: Int = R.layout.item_type_1

        override fun bind(view: View) {
            view.text.text = content.text
            view.setOnClickListener { onClick.invoke(content) }
        }
    }

    class ItemType2(
        override val content: MainItemContent,
        val onClick: (MainItemContent) -> Unit
    ) : Item {
        override val layout: Int = R.layout.item_type_2

        override fun bind(view: View) {
            view.text.text = content.text
            view.setOnClickListener { onClick.invoke(content) }
        }
    }

    data class MainItemContent(override val id: Int, val text: String): ItemContent
}