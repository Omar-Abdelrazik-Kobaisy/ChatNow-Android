package com.example.chat.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chat.R
import com.example.chat.databinding.ItemMessageBinding
import com.example.chat.databinding.ItemRecievedMessageBinding
import com.example.chat.databinding.ItemSentMessageBinding
import com.example.chat.db.models.Message
import com.example.chat.ui.UserProvider


enum class MessageType (val value : Int)
{
    Sent(1),
    Received(2)
}

class MessageAdapter(var messages: MutableList<Message>): Adapter<ViewHolder>() {
    class SentMessageViewHolder(val binding: ItemSentMessageBinding) : ViewHolder(binding.messageLayout){
        fun bind(message: Message) {
            binding.message = message
        }

    }class ReceivedMessageViewHolder(val binding: ItemRecievedMessageBinding) : ViewHolder(binding.messageLayout){
        fun bind(message: Message) {
            binding.message = message
        }

    }

    override fun getItemViewType(position: Int): Int {
        return  if(messages[position].senderId == UserProvider.user?.id)
                    MessageType.Sent.value
                else
                    MessageType.Received.value
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (viewType == MessageType.Sent.value)
        {
            val viewBinding : ItemSentMessageBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_sent_message,parent,false)

            return SentMessageViewHolder(viewBinding)
        }else {
            val viewBinding: ItemRecievedMessageBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recieved_message, parent, false
            )

            return ReceivedMessageViewHolder(viewBinding)
        }
    }

    fun addMessage(message :Message )
    {
        messages.add(message)
        notifyItemInserted(messages.size)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is SentMessageViewHolder)
        {
//            holder.binding.message = messages[position]
//            holder.binding.invalidateAll()
            holder.bind(messages[position])

        }else if(holder is ReceivedMessageViewHolder){
//            holder.binding.message = messages[position]
//            holder.binding.invalidateAll()
            holder.bind(messages[position])
        }


    }

    override fun getItemCount(): Int = messages.size
}