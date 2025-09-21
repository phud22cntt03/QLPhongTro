package com.example.qunlphngtr

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qunlphngtr.adapter.TenantAdapter
import com.example.qunlphngtr.model.Tenant

class TenantListActivity : AppCompatActivity() {

    private lateinit var tenantAdapter: TenantAdapter
    private lateinit var tenantList: MutableList<Tenant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = findViewById<SearchView>(R.id.searchView)

        // Dữ liệu mẫu
        tenantList = mutableListOf(
            Tenant("Nguyễn Văn A", "Nam", "09098303802"),
            Tenant("Trần Thị B", "Nữ", "0920852302"),
            Tenant("Lê Văn C", "Nam", "093824802")
        )

        tenantAdapter = TenantAdapter(tenantList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tenantAdapter

        // Search theo tên hoặc số điện thoại
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query.isNullOrEmpty()) {
            tenantAdapter.filterList(tenantList) // Trả về danh sách gốc
        } else {
            val searchText = query.lowercase()
            val filteredList = tenantList.filter {
                it.name.lowercase().contains(searchText) ||
                        it.phone.contains(searchText)
            }
            tenantAdapter.filterList(filteredList)
        }
    }
}
