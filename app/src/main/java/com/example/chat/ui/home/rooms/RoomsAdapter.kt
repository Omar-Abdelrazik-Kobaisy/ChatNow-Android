package com.example.chat.ui.home.rooms

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.chat.R
import com.example.chat.databinding.ItemRoomBinding
import com.example.chat.db.models.Room

class RoomsAdapter(var items : List<Room?>? ):Adapter<RoomsAdapter.ViewHolder>() {

    var onItemClickListener:OnItemClickListener? = null

    class ViewHolder(val binding: ItemRoomBinding):androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.cardView){
        fun bind(room: Room) {
            binding.item = room
            binding.invalidateAll()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewBinding : ItemRoomBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_room,parent,false)

        return  ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position) ?: Room("","","","",""))
        holder.binding.cardView.setOnClickListener {
            onItemClickListener.let {
                it?.onItemClick(position, items?.get(position) ?: Room("","","","",""))
            }
        }

    }

    override fun getItemCount(): Int = items?.size ?: 0
    fun updateList(rooms : List<Room?>?) {
        items = rooms
        notifyDataSetChanged()
    }
}