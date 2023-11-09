package com.example.myapplication.color.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ListItemColorBinding
import com.example.myapplication.color.presentation.list.model.ColorListModel

class ColorListAdapter(
    private val onItemClick: (id: Long) -> Unit
) : ListAdapter<ColorListModel, ColorListAdapter.ColorViewHolder>(
    ColorDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(
            onItemClick,
            ListItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bindData(currentList[position])
    }

    class ColorViewHolder(
        private val onItemClick: (id: Long) -> Unit,
        private val binding: ListItemColorBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(color: ColorListModel) {
            binding.userName.text = color.userName
            binding.createDate.text = color.createDate
            Glide.with(itemView.context)
                .load(color.imageUrl)
                .centerCrop()
                .into(binding.image)

            itemView.setOnClickListener {
                onItemClick(color.id)
            }
        }
    }
}

class ColorDiffCallback : DiffUtil.ItemCallback<ColorListModel>() {
    override fun areItemsTheSame(oldItem: ColorListModel, newItem: ColorListModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ColorListModel, newItem: ColorListModel): Boolean {
        return oldItem == newItem && oldItem == newItem
    }
}