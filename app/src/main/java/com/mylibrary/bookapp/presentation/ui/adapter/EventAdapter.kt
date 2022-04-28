package com.mylibrary.bookapp.presentation.ui.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mylibrary.core.domain.Event
import mylibrary.bookapp.databinding.ItemEventBinding

class EventAdapter(
    private val isDelete: Boolean,
    private val listener: (Event) -> Unit
) : ListAdapter<Event, EventAdapter.EventViewHolder>(
    DiffCallback()
) {

    val deleteItem = MutableLiveData<Pair<Int, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding, listener, deleteItem, isDelete)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(
            oldItem: Event,
            newItem: Event
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Event,
            newItem: Event
        ) = oldItem == newItem
    }

    class EventViewHolder(
        private val binding: ItemEventBinding,
        private val listener: (Event) -> Unit,
        private val deleteItem: MutableLiveData<Pair<Int, Int>>,
        private val isDelete: Boolean
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.apply {
                if(!event.avatar.startsWith("/storage/")) {
                    val myBitmap = BitmapFactory.decodeFile(event.avatar)
                    ivEvent.setImageBitmap(myBitmap)
                }else Glide.with(binding.root.context).load(event.avatar).into(ivEvent)
                tvTitle.text = event.first_name
                tvDesc.text = event.last_name
                tvContact.text = event.email
                tvNum.text = event.id.toString()

                if (isDelete) ibDelete.visibility = View.VISIBLE

            }

            binding.ibDelete.setOnClickListener {
                deleteItem.postValue(Pair(adapterPosition, event.id))
            }

            binding.root.setOnClickListener {
                listener(event)
            }
        }
    }
}