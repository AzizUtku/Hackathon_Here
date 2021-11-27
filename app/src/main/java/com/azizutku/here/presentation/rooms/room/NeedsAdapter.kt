package com.azizutku.here.presentation.rooms.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azizutku.here.R
import com.azizutku.here.data.model.Need
import com.azizutku.here.data.model.Room
import com.azizutku.here.databinding.ItemActivityBinding
import com.azizutku.here.databinding.ItemNeedBinding
import com.azizutku.here.extensions.setVisible

class NeedsAdapter : ListAdapter<Need, NeedsAdapter.NeedViewHolder>(needDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemNeedBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_need,
            parent,
            false
        )
        return NeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class NeedViewHolder(val binding: ItemNeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(need: Need) {
            binding.txtTitle.text = need.title
            binding.txtDesc.text = need.description
            binding.txtFilled.text = need.filled.toString()
            binding.txtNeeded.text = need.needed.toString()
        }

    }

    object needDiffCallback : DiffUtil.ItemCallback<Need>() {
        override fun areItemsTheSame(oldItem: Need, newItem: Need): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Need, newItem: Need): Boolean {
            return oldItem == newItem
        }
    }

}