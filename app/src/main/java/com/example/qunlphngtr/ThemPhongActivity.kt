package com.example.qunlphngtr

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ThemPhongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_phong)

        val edtTenPhong = findViewById<EditText>(R.id.edtTenPhong)
        val edtGiaPhong = findViewById<EditText>(R.id.edtGiaPhong)
        val edtDienTich = findViewById<EditText>(R.id.edtDienTich)
        val btnLuu = findViewById<Button>(R.id.btnLuu)
        val btnHuy = findViewById<Button>(R.id.btnHuy)

        btnLuu.setOnClickListener {
            val ten = edtTenPhong.text.toString()
            val gia = edtGiaPhong.text.toString()
            val dientich = edtDienTich.text.toString()

            if (ten.isEmpty() || gia.isEmpty() || dientich.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Đã thêm phòng: $ten", Toast.LENGTH_SHORT).show()
                finish() // đóng activity quay lại
            }
        }

        btnHuy.setOnClickListener {
            finish() // thoát về trang trước
        }
    }
}
