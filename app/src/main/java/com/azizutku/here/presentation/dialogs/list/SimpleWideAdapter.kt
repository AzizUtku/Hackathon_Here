package com.azizutku.here.presentation.dialogs.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.azizutku.here.R
import com.azizutku.here.databinding.ItemSimpleWideBinding
import java.util.*

class SimpleWideAdapter<T,V>: RecyclerView.Adapter<SimpleWideAdapter<T,V>.SimpleWideViewHolder<T,V>>() {
    private val list = ArrayList<Pair<T, V>>()

    private val UNSET_CALLBACK: (key: T, value: V) -> Unit = { _: T, _: V ->
        throw Exception("You must set onItemClicked")
    }
    private var onItemClicked: (key: T, value: V) -> Unit = UNSET_CALLBACK

    fun setList(list: List<Pair<T, V>>) {
        this.list.clear()
        this.list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleWideViewHolder<T,V> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemSimpleWideBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_simple_wide,
            parent,
            false
        )
        return SimpleWideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimpleWideViewHolder<T,V>, position: Int) {
        val pair = list[position]
        holder.bind(pair)
        holder.binding.root.setOnClickListener {
            onItemClicked(pair.first, pair.second)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setOnItemClicked(onItemClicked: (key: T, value: V) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    inner class SimpleWideViewHolder<T,V>(val binding: ItemSimpleWideBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pair: Pair<T, V>) {
            binding.itemSimpleWideTxtItem.text = pair.second.toString()
        }
    }
}