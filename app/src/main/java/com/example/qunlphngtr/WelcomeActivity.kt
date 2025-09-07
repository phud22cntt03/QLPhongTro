package com.example.qunlphngtr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome) // gắn với file XML bạn đã viết

        val nextButton: Button = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            // Chuyển sang MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // đóng WelcomeActivity, không cho quay lại
        }
    }
}