package com.example.navigationcomponent.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.DonutItemBinding
import com.example.navigationcomponent.model.Donut

class DonutListAdapter   (private var onEdit: (Donut) -> Unit,
                          private var onDelete: (Donut) -> Unit
) : ListAdapter<Donut, DonutListAdapter.DonutListViewHolder>(DonutDiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DonutListViewHolder(
        DonutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onEdit,
        onDelete
    )

    override fun onBindViewHolder(holder: DonutListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class DonutListViewHolder(
        private val binding: DonutItemBinding,
        private var onEdit: (Donut) -> Unit,
        private var onDelete: (Donut) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var donutId: Long = -1
        private var nameView = binding.name
        private var description = binding.description
        private var thumbnail = binding.thumbnail
        private var rating = binding.rating
        private var donut: Donut? = null

        fun bind(donut: Donut) {
            donutId = donut.id
            nameView?.text = donut.name
            description?.text = donut.description
            rating?.text = donut.rating.toString()
            thumbnail.setImageResource(R.drawable.donut_with_sprinkles)
            this.donut = donut
            binding.deleteButton.setOnClickListener {
                onDelete(donut)
            }
            binding.root.setOnClickListener {
                onEdit(donut)
            }
        }
    }
}

class DonutDiffCallback : DiffUtil.ItemCallback<Donut>() {
    override fun areItemsTheSame(oldItem: Donut, newItem: Donut): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Donut, newItem: Donut): Boolean {
        return oldItem == newItem
    }
}