package com.example.qunlphngtr
import android.widget.EditText
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.widget.Toast

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

class QuanLyPhongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_list)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Chọn mặc định là Home
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_bill -> {
                    startActivity(Intent(this, BillActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
        val btnThemPhong = findViewById<Button>(R.id.btnAddRoom) // nút mở popup

        btnThemPhong.setOnClickListener {
            // Tạo Dialog
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.activity_them_phong) // dùng layout bạn đã tạo

            // Tìm các view trong popup
            val edtTenPhong = dialog.findViewById<EditText>(R.id.edtTenPhong)
            val edtGiaPhong = dialog.findViewById<EditText>(R.id.edtGiaPhong)
            val edtDienTich = dialog.findViewById<EditText>(R.id.edtDienTich)
            val btnLuu = dialog.findViewById<Button>(R.id.btnLuu)
            val btnHuy = dialog.findViewById<Button>(R.id.btnHuy)

            // Xử lý nút Lưu
            btnLuu.setOnClickListener {
                val tenPhong = edtTenPhong.text.toString()
                val giaPhong = edtGiaPhong.text.toString()
                val dienTich = edtDienTich.text.toString()
                if(tenPhong.isBlank()||giaPhong.isBlank()||dienTich.isBlank())
                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                else{
                    Toast.makeText(this, "Đã thêm phòng: $tenPhong", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }

            // Nút Hủy
            btnHuy.setOnClickListener {
                dialog.dismiss()
            }

            // Tùy chỉnh style (bo tròn, full width)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.show()
        }


    }
}
