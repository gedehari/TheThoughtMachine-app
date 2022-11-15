package com.gedehari.thethoughtmachine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gedehari.thethoughtmachine.data.Thought
import com.gedehari.thethoughtmachine.databinding.ThoughtListItemBinding

class ThoughtListAdapter :
    ListAdapter<Thought, ThoughtListAdapter.ThoughtListViewHolder>(DiffCallback) {
    class ThoughtListViewHolder(private var binding: ThoughtListItemBinding) :
        ViewHolder(binding.root) {
        fun bind(thought: Thought) {
            binding.apply {
                thoughtTitle.text = thought.title
                thoughtAuthor.text = thought.author
                thoughtMessage.text = thought.message
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThoughtListViewHolder {
        return ThoughtListViewHolder(ThoughtListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ThoughtListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : ItemCallback<Thought>() {
            override fun areContentsTheSame(oldItem: Thought, newItem: Thought): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Thought, newItem: Thought): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
