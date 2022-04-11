package com.mylibrary.bookapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mylibrary.bookapp.databinding.ItemEventBinding

class EventAdapter(private val listener: (com.mylibrary.core.domain.Event) -> Unit) : ListAdapter<com.mylibrary.core.domain.Event, EventAdapter.EventViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<com.mylibrary.core.domain.Event>() {
        override fun areItemsTheSame(oldItem: com.mylibrary.core.domain.Event, newItem: com.mylibrary.core.domain.Event) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: com.mylibrary.core.domain.Event, newItem: com.mylibrary.core.domain.Event) = oldItem == newItem
    }

    class EventViewHolder(private val binding: ItemEventBinding, private val listener: (com.mylibrary.core.domain.Event) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: com.mylibrary.core.domain.Event) {
            binding.apply {
                Glide.with(binding.root.context).load(event.avatar).into(ivEvent);
                tvTitle.text = event.first_name
                tvDesc.text = event.last_name
                tvContact.text = event.email
                tvNum.text = event.id.toString()
            }

            binding.root.setOnClickListener {
                listener(event)
            }
        }
    }
}