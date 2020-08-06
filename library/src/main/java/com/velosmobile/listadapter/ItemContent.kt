package com.velosmobile.listadapter

interface ItemContent {
    val id: Any
    override fun equals(other: Any?): Boolean
}