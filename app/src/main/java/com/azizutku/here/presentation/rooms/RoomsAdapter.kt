package com.azizutku.here.presentation.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azizutku.here.R
import com.azizutku.here.data.model.Room
import com.azizutku.here.databinding.ItemActivityBinding
import com.azizutku.here.extensions.setVisible

class RoomsAdapter : ListAdapter<Room, RoomsAdapter.RoomViewHolder>(roomDiffCallback) {

    private var widthPercent = 1f

    private val UNSET_CALLBACK: (room: Room) -> Unit = { _: Room ->
        throw Exception("You must set onItemClicked")
    }
    private var onItemClicked: (room: Room) -> Unit = UNSET_CALLBACK

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemActivityBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_activity,
            parent,
            false
        )
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setOnItemClicked(onItemClicked: (room: Room) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    fun setWidthPercent(widthPercent: Float) {
        this.widthPercent = widthPercent
    }

    inner class RoomViewHolder(val binding: ItemActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(room: Room) {
            binding.txtTopic.text = room.topic
            binding.txtTitle.text = room.title
            binding.imageLogo.setBackgroundResource(room.iconId)
            binding.txtDesc.text = room.description
            binding.txtNeeds.text = room.needs.toString()
            binding.txtParticipants.text = room.participants.toString()
            binding.txtLocation.text = room.location ?: "Not specified"
            binding.imgPrivate.setVisible(room.isPrivate)
            binding.root.setOnClickListener {
                onItemClicked(room)
            }
        }
    }

    object roomDiffCallback : DiffUtil.ItemCallback<Room>() {
        override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem == newItem
        }
    }

}