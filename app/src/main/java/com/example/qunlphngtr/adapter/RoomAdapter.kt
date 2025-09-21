package com.example.qunlphngtr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qunlphngtr.model.Room
import com.example.qunlphngtr.R
class RoomAdapter(private val list: MutableList<Room>) :
    RecyclerView.Adapter<RoomAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        
        val tvName: TextView = view.findViewById(R.id.tvRoomName)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvDesc: TextView = view.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val r = list[position]
        holder.tvName.text = r.name
        holder.tvStatus.text = "Trạng thái: ${r.status}"
        holder.tvDesc.text = r.description
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: List<Room>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun addRoom(room: Room) {
        list.add(0, room)
        notifyItemInserted(0)
    }
}