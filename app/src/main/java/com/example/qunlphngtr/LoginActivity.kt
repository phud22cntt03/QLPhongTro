package com.example.qunlphngtr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qunlphngtr.MainActivity
import com.example.qunlphngtr.R
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Liên kết với layout activity_login.xml
        setContentView(R.layout.activity_login)

        // Ánh xạ view bằng findViewById
        val inputEmail = findViewById<TextInputEditText>(R.id.inputPhone)
        val inputPassword = findViewById<TextInputEditText>(R.id.inputPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        // Xử lý nút Đăng Nhập
        btnLogin.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
//            if (email.isEmpty() || password.isEmpty()) {
//                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
//            } else if (email == "admin@gmail.com" && password == "123456") {
//                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            } else {
//                Toast.makeText(this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show()
//            }

        }

        // Xử lý nút Đăng Ký
        tvRegister.setOnClickListener {
            startActivity(Intent(this, "RegisterActivity"::class.java))
        }
    }
}
