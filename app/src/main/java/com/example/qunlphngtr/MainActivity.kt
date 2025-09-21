package com.example.qunlphngtr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView  // Thêm dòng này

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val card2 = findViewById<MaterialCardView>(R.id.card2)
        card2.setOnClickListener {
            val intent = Intent(this, TenantListActivity::class.java)
            startActivity(intent)
        }
    }
}
