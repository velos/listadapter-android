package com.velosmobile.listadapter.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.velosmobile.listadapter.Item
import com.velosmobile.listadapter.ItemContent
import com.velosmobile.listadapter.ListAdapter
import com.velosmobile.listadapter.R
import com.velosmobile.listadapter.app.viewmodel.ItemContentBlue
import com.velosmobile.listadapter.app.viewmodel.ItemContentRed
import com.velosmobile.listadapter.app.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private val adapter = ListAdapter()

    private val onRedItemClickListener: (ItemContentRed) -> Unit = { content ->
        Toast.makeText(this, "Red item: ${content.text}", Toast.LENGTH_SHORT).show()
    }

    private val onBlueItemClickListener: (ItemContentBlue) -> Unit = { content ->
        Toast.makeText(this, "Blue item: ${content.text}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        refresh_button.setOnClickListener { viewModel.refresh() }
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        viewModel.listItems.observe(this, Observer { adapter.submitList(it.toItemList()) })

        viewModel.refresh()
    }

    private fun List<ItemContent>.toItemList(): List<Item> {
        return map {
            when (it) {
                is ItemContentRed -> {
                    ItemRed(it, onRedItemClickListener)
                }
                is ItemContentBlue -> {
                    ItemBlue(it, onBlueItemClickListener)
                }
                else -> {
                    throw IllegalArgumentException("Unsupported type: ${it::class.java}")
                }
            }
        }
    }
}