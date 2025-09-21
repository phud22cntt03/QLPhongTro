
package com.example.qunlphngtr
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BillDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_detail)

//        // Lấy dữ liệu từ Intent
//        val invoiceId = intent.getIntExtra("invoice_id", -1)
//
//        // TODO: gọi DB/API để load dữ liệu hóa đơn theo invoiceId
//        val textView = findViewById<TextView>(R.id.txtInvoiceDetail)
//        textView.text = "Chi tiết hóa đơn #$invoiceId"
    }
}
