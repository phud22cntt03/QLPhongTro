package com.example.qunlphngtr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qunlphngtr.adapter.TenantAdapter
import com.example.qunlphngtr.model.Tenant
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TenantListActivity : AppCompatActivity() {

    private lateinit var tenantAdapter: TenantAdapter
    private lateinit var tenantList: MutableList<Tenant>
    private val ADD_TENANT_REQUEST = 100   // request code cho AddTenantActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = findViewById<SearchView>(R.id.searchView)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAddTenant)

        // Dữ liệu mẫu ban đầu
        tenantList = mutableListOf(
            Tenant("Nguyễn Văn A", "Nam", "09098303802"),
            Tenant("Trần Thị B", "Nữ", "0920852302"),
            Tenant("Lê Văn C", "Nam", "093824802")
        )

        tenantAdapter = TenantAdapter(tenantList)

        // ✅ Sửa: click 1 lần
        tenantAdapter.onItemClick = { tenant, pos ->
            showEditDialog(tenant, pos)
        }

        // ✅ Xóa: nhấn giữ
        tenantAdapter.onItemLongClick = { tenant, pos ->
            AlertDialog.Builder(this)
                .setTitle("Xóa người thuê")
                .setMessage("Bạn có chắc muốn xóa ${tenant.name}?")
                .setPositiveButton("Xóa") { _, _ ->
                    tenantAdapter.removeAt(pos)
                    tenantList.removeAt(pos)
                }
                .setNegativeButton("Hủy", null)
                .show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tenantAdapter

        // ✅ Thêm mới: mở màn hình nhập thông tin
        fabAdd.setOnClickListener {
            val intent = Intent(this, AddTenantActivity::class.java)
            startActivityForResult(intent, ADD_TENANT_REQUEST)
        }

        // ✅ Tìm kiếm
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

    // Dialog sửa thông tin đầy đủ
    private fun showEditDialog(tenant: Tenant, pos: Int) {
        val layout = layoutInflater.inflate(R.layout.dialog_edit_tenant, null)
        val edtName = layout.findViewById<EditText>(R.id.edtName)
        val edtGender = layout.findViewById<EditText>(R.id.edtGender)
        val edtPhone = layout.findViewById<EditText>(R.id.edtPhone)

        edtName.setText(tenant.name)
        edtGender.setText(tenant.gender)
        edtPhone.setText(tenant.phone)

        AlertDialog.Builder(this)
            .setTitle("Sửa thông tin")
            .setView(layout)
            .setPositiveButton("Lưu") { _, _ ->
                val updatedTenant = tenant.copy(
                    name = edtName.text.toString(),
                    gender = edtGender.text.toString(),
                    phone = edtPhone.text.toString()
                )
                tenantList[pos] = updatedTenant
                tenantAdapter.updateItem(pos, updatedTenant)
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    // Nhận kết quả từ AddTenantActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_TENANT_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.let {
                val name = it.getStringExtra("name") ?: ""
                val gender = it.getStringExtra("gender") ?: ""
                val phone = it.getStringExtra("phone") ?: ""
                val newTenant = Tenant(name, gender, phone)

                tenantList.add(newTenant)
                tenantAdapter.filterList(tenantList) // refresh danh sách
            }
        }
    }

    private fun filterList(query: String?) {
        if (query.isNullOrEmpty()) {
            tenantAdapter.filterList(tenantList)
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
