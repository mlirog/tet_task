package com.maris_skrivelis.tet_task.ui.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maris_skrivelis.tet_task.databinding.RowResultItemBinding
import kotlin.properties.Delegates

class SearchResultsAdapter(
    private val onResultClick: (result: String) -> Unit,
) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    var results: List<String> by Delegates.observable(emptyList(), { _, old, new ->
        DiffUtil.calculateDiff(TextDiff(old, new)).dispatchUpdatesTo(this)
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(RowResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.binding.result = result
        holder.binding.avatarBack.setOnClickListener {
            onResultClick(result)
        }
    }

    override fun getItemCount() = results.size

    inner class ViewHolder(val binding: RowResultItemBinding) : RecyclerView.ViewHolder(binding.root)

    inner class TextDiff(
        private val oldItems: List<String>,
        private val newItems: List<String>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldItems.size

        override fun getNewListSize() = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems[oldItemPosition] == newItems[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldItems == newItems
    }
}
