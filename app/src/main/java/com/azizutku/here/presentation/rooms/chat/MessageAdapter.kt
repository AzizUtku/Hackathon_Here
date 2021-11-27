package com.azizutku.here.presentation.rooms.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azizutku.here.R
import com.azizutku.here.data.model.Message
import com.azizutku.here.data.model.Need
import com.azizutku.here.data.model.Room
import com.azizutku.here.databinding.ItemActivityBinding
import com.azizutku.here.databinding.ItemMessageReceiverBinding
import com.azizutku.here.databinding.ItemMessageSenderBinding
import com.azizutku.here.databinding.ItemNeedBinding
import com.azizutku.here.extensions.setVisible

private const val TYPE_RECEIVER = 0
private const val TYPE_SENDER = 1

class MessageAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(messageDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        if (viewType == TYPE_SENDER) {
            val binding: ItemMessageSenderBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_message_sender,
                parent,
                false
            )
            return SenderViewHolder(binding)
        } else {
            val binding: ItemMessageReceiverBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_message_receiver,
                parent,
                false
            )
            return ReceiverViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_SENDER) {
            (holder as SenderViewHolder).bind(getItem(position))
        } else {
            (holder as ReceiverViewHolder).bind(getItem(position))
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isReceived) {
            TYPE_RECEIVER
        } else {
            TYPE_SENDER
        }
    }

    inner class SenderViewHolder(val binding: ItemMessageSenderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.txtTime.text = message.time
            binding.txtBody.text = message.message
        }

    }

    inner class ReceiverViewHolder(val binding: ItemMessageReceiverBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.txtTime.text = message.time
            binding.txtBody.text = message.message
            binding.txtName.text = message.senderName
        }

    }

    object messageDiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }
    }


}