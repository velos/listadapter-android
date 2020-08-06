package com.velosmobile.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * @param placeholderLayout is displayed when the current item from the [PagedList] is a null placeholder. This is useful when using a [androidx.paging.PositionalDataSource] for items where data is not yet loaded.
 * @param loadingLayout is displayed at the top or bottom of the list when [isLoadingBefore] or [isLoadingAfter] is true. This is useful when using a [androidx.paging.ContiguousDataSource] when loading data before or after a requested key.
 */
class PagedListAdapter(
    @LayoutRes private val placeholderLayout: Int,
    @LayoutRes private val loadingLayout: Int = -1
) : PagedListAdapter<Item, RecyclerView.ViewHolder>(ItemDiffCallback()) {
    var isLoadingBefore: Boolean = false
        set(value) {
            if (loadingLayout < 0) throw IllegalArgumentException("loadingLayout not set.")

            val oldValue = field
            field = value

            if (field && !oldValue) notifyItemInserted(0)
            else if (!field && oldValue) notifyItemRemoved(0)
        }

    var isLoadingAfter: Boolean = false
        set(value) {
            if (loadingLayout < 0) throw IllegalArgumentException("loadingLayout not set.")

            val oldValue = field
            field = value

            if (value && !oldValue) notifyItemInserted(itemCount - 1)
            else if (!value && oldValue) notifyItemRemoved(itemCount)
        }

    override fun getItemCount(): Int {
        return super.getItemCount() +
                (if (isLoadingBefore) 1 else 0) +
                (if (isLoadingAfter) 1 else 0)
    }

    override fun getItemViewType(position: Int): Int {
        val itemPosition = if (isLoadingBefore) position - 1 else position

        // Need to return a constant for placeholderLayout & loadingLayout in case those layouts are shared with a "real item".
        return if (isLoadingBefore && position == 0) VIEW_TYPE_LOADING
        else if (isLoadingAfter && position == itemCount - 1) VIEW_TYPE_LOADING
        else getItem(itemPosition)?.layout ?: VIEW_TYPE_PLACEHOLDER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_PLACEHOLDER -> placeholderLayout
            VIEW_TYPE_LOADING -> loadingLayout
            else -> viewType
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemPosition = if (isLoadingBefore) position - 1 else position

        if (itemPosition >= 0 && itemPosition < super.getItemCount()) {
            getItem(itemPosition)?.bind(holder.itemView)
        } // else item is loading layout: do nothing
    }

    companion object {
        private const val VIEW_TYPE_PLACEHOLDER = -1
        private const val VIEW_TYPE_LOADING = -2
    }
}