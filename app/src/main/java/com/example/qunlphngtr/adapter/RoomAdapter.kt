package com.example.qunlphngtr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qunlphngtr.R
import com.example.qunlphngtr.model.Room
import android.net.Uri



class RoomAdapter(
    private val roomList: MutableList<Room>,
    private val onItemClick: (Room) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]

        holder.tvName.text = room.name
        holder.tvStatus.text = "Trạng thái: ${room.status}"
        holder.tvDescription.text = room.description

        // ✅ Load ảnh bằng Glide (ổn định hơn setImageURI)
        if (!room.imageUri.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(Uri.parse(room.imageUri))
                .placeholder(R.drawable.ic_launcher_background) // ảnh mặc định khi chưa có ảnh
                .into(holder.imgRoom)
        } else {
            holder.imgRoom.setImageResource(R.drawable.ic_launcher_background)
        }

        // Sự kiện click item
        holder.itemView.setOnClickListener {
            onItemClick(room)
        }
    }

    override fun getItemCount(): Int = roomList.size

    fun addRoom(room: Room) {
        roomList.add(room)
        notifyItemInserted(roomList.size - 1)
    }

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvRoomName)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val imgRoom: ImageView = itemView.findViewById(R.id.imgRoom)
    }
}