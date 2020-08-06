package com.velosmobile.listadapter.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.velosmobile.listadapter.ItemContent

class MainViewModel : ViewModel() {
    val listItems = MutableLiveData<List<ItemContent>>(emptyList())

    fun refresh() {
        val data = mutableListOf<ItemContent>()

        for (i in 0..10) {
            if (i % 2 == 0) {
                data.add(
                    ItemContentRed(
                        id = i,
                        text = i.toString()
                    )
                )
            } else {
                data.add(
                    ItemContentBlue(
                        id = i,
                        text = i.toString()
                    )
                )
            }
        }

        listItems.postValue(data.apply { shuffle() })
    }
}