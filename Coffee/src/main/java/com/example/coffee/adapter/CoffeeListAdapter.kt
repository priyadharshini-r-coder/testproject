package com.example.coffee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee.R
import com.example.coffee.databinding.CoffeeItemBinding
import com.example.core.model.Coffee

class CoffeeListAdapter (private var onEdit: (Coffee) -> Unit,
                         private var onDelete: (Coffee) -> Unit
) : ListAdapter<Coffee, CoffeeListAdapter.CoffeeListViewHolder>(CoffeeDiffCallback()) {

    class CoffeeListViewHolder(
        private val binding: CoffeeItemBinding,
        private var onEdit: (Coffee) -> Unit,
        private var onDelete: (Coffee) -> Unit
        ) : RecyclerView.ViewHolder(binding.root) {

            private var coffeeId: Long = -1
            private var nameView = binding.name
            private var description = binding.description
            private var thumbnail = binding.thumbnail
            private var rating = binding.rating
            private var coffee: Coffee? = null

            fun bind(coffee: Coffee) {
                coffeeId = coffee.id
                nameView.text = coffee.name
                description.text = coffee.description
                rating.text = coffee.rating.toString()
                thumbnail.setImageResource(R.drawable.coffe_cup)
                this.coffee = coffee
                binding.deleteButton.setOnClickListener {
                    onDelete(coffee)
                }
                binding.root.setOnClickListener {
                    onEdit(coffee)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  CoffeeListViewHolder(
            CoffeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onEdit,
            onDelete
        )

        override fun onBindViewHolder(holder: CoffeeListViewHolder, position: Int) {
            holder.bind(getItem(position))
        }
    }

    class CoffeeDiffCallback : DiffUtil.ItemCallback<Coffee>() {
        override fun areItemsTheSame(oldItem: Coffee, newItem: Coffee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coffee, newItem: Coffee): Boolean {
            return oldItem == newItem
        }
    }
