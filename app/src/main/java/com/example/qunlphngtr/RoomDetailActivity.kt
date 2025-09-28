package com.example.qunlphngtr
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.qunlphngtr.model.Room

import android.net.Uri

class RoomDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_detail) // layout bạn đang dùng

        // Ánh xạ (theo layout bạn đã gửi trước: tvRoomId, tvTenantName, tvTenantPhone, tvBillInfo, imgRoom)
        val tvRoomId = findViewById<TextView>(R.id.tvRoomId)
        val tvTenantName = findViewById<TextView>(R.id.tvTenantName)
        val tvTenantPhone = findViewById<TextView>(R.id.tvTenantPhone)
        val tvBillInfo = findViewById<TextView>(R.id.tvBillInfo)
        val imgRoom = findViewById<ImageView>(R.id.imgRoom)

        // Lấy dữ liệu từ Intent (QuanLyPhongActivity đã gửi các extras này)
        val roomId = intent.getIntExtra("room_id", -1)
        val roomName = intent.getStringExtra("room_name")
        val roomPrice = intent.getDoubleExtra("room_price", 0.0)
        val roomArea = intent.getDoubleExtra("room_area", 0.0)
        val roomStatus = intent.getStringExtra("room_status")
        val roomDescription = intent.getStringExtra("room_description")
        val roomImage = intent.getStringExtra("room_image") // URI dưới dạng String

        // Gán dữ liệu lên view (chọn mapping phù hợp với layout hiện có)
        // tvRoomId: show tên phòng (nếu có) hoặc mã P{id} nếu bạn muốn
        tvRoomId.text = when {
            !roomName.isNullOrBlank() -> roomName
            roomId != -1 -> "P$roomId"
            else -> "—"
        }

        // tvTenantName: dùng để show mô tả hoặc tên người thuê (nếu bạn có)
        tvTenantName.text = if (!roomDescription.isNullOrBlank()) roomDescription else "Chưa có mô tả"

        // tvTenantPhone: dùng để show trạng thái phòng (bạn có thể thay)
        tvTenantPhone.text = if (!roomStatus.isNullOrBlank()) "Trạng thái: $roomStatus" else "Trạng thái: —"

        // tvBillInfo: show giá + diện tích
        val priceText = if (roomPrice > 0.0) "Giá: ${roomPrice.toLong()}đ" else "Giá: —"
        val areaText = if (roomArea > 0.0) "Diện tích: ${roomArea} m²" else "Diện tích: —"
        tvBillInfo.text = "$priceText\n$areaText"

        // Load ảnh: nếu lưu URI string thì dùng setImageURI; nếu bạn dùng URL thì dùng Glide/Picasso (nếu có)
        if (!roomImage.isNullOrBlank()) {
            try {
                imgRoom.setImageURI(Uri.parse(roomImage))
            } catch (e: Exception) {
                imgRoom.setImageResource(R.drawable.ic_home) // drawable mặc định
            }
        } else {
            imgRoom.setImageResource(R.drawable.ic_home)
        }
    }
}