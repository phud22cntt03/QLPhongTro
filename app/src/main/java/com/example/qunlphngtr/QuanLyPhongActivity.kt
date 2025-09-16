/*
package com.example.qunlphngtr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class QuanLyPhongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_list)

        // Ánh xạ bottom nav
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Chọn mặc định là Trang chủ
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Đang ở trang Home (QuanLyPhongActivity) -> không cần chuyển
                    true
                }
                R.id.nav_bill -> {
                    // Chuyển sang BillActivity
                    startActivity(Intent(this, BillActivity::class.java))
                    overridePendingTransition(0, 0) // bỏ animation
                    true
                }
                R.id.nav_settings -> {
                    // Chuyển sang SettingsActivity (nếu có)
                    startActivity(Intent(this, SettingsActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
    }
}
*/
