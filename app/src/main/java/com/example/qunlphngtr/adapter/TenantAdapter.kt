package com.example.qunlphngtr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qunlphngtr.R
import com.example.qunlphngtr.model.Tenant


class TenantAdapter(private var tenantList: List<Tenant>) :
    RecyclerView.Adapter<TenantAdapter.TenantViewHolder>() {

    inner class TenantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvGender: TextView = itemView.findViewById(R.id.tvGender)
        val tvPhone: TextView = itemView.findViewById(R.id.tvPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tenant, parent, false)
        return TenantViewHolder(view)
    }

    override fun onBindViewHolder(holder: TenantViewHolder, position: Int) {
        val tenant = tenantList[position]
        holder.tvName.text = tenant.name
        holder.tvGender.text = tenant.gender
        holder.tvPhone.text = tenant.phone
    }

    override fun getItemCount(): Int = tenantList.size

    fun filterList(filteredList: List<Tenant>) {
        tenantList = filteredList
        notifyDataSetChanged()
    }
}
