package com.mylibrary.bookapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mylibrary.core.domain.DataX
import mylibrary.bookapp.databinding.ItemBookBinding
import java.util.*
import kotlin.collections.ArrayList

class BookAdapter : ListAdapter<DataX, BookAdapter.BookViewHolder>(DiffCallback()), Filterable {

    private var list = mutableListOf<DataX>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<DataX>() {
        override fun areItemsTheSame(
            oldItem: DataX,
            newItem: DataX
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: DataX,
            newItem: DataX
        ) = oldItem == newItem
    }

    class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: DataX?) {
            binding.apply {
                tvBookName.text = book?.name
                tvBookDate.text = book?.pantone_value
                tvBookPages.text = book?.year.toString()
            }
        }
    }

    fun setData(list: MutableList<DataX>?){
        this.list = list!!
        submitList(list)
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<DataX>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {
                    if (item.name.lowercase(Locale.getDefault()).startsWith(constraint.toString()
                            .lowercase(Locale.getDefault()))) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(filterResults?.values as MutableList<DataX>)
        }
    }
}