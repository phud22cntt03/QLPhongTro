package com.example.qunlphngtr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // load giao diện activity_main.xml
        val cardQuanLyPhongActivity = findViewById<MaterialCardView>(R.id.card1)
        /*cardQuanLyPhongActivity.setOnClickListener {
            val intent = Intent(this, QuanLyPhongActivity::class.java)
            startActivity(intent)
        }*/
    }
}
