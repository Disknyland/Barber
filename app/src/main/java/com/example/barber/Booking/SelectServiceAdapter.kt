package com.example.barber.Booking

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barber.databinding.ShowserviceLayoutBinding

class SelectServiceAdapter (val items : List<Service>,val context : Context):
    RecyclerView.Adapter<SelectServiceAdapter.ViewHolder>(){
        inner class ViewHolder(view: View, val binding : ShowserviceLayoutBinding) : RecyclerView.ViewHolder(view) {
            init {
                binding.select.setOnClickListener{
                    val item = items[adapterPosition]
                    val context_v: Context = view.context
                    val intent = Intent(context_v, SelectTimeActivity::class.java)
                    intent.putExtra("SeID", item.SE_ID.toString())
                    intent.putExtra("SeName", item.SE_Name)
                    intent.putExtra("SePrice", item.SE_Price.toString())
                    Log.d("Service Info : ",item.toString())
                    context_v.startActivity(intent)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectServiceAdapter.ViewHolder {
        val binding = ShowserviceLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: SelectServiceAdapter.ViewHolder, position: Int) {
        val binding_holder = holder.binding
        var se_name = items[position].SE_Name
        var se_price = items[position].SE_Price.toString()
        binding_holder.tvName.text = "${se_name}"
        binding_holder.tvPrice.text = "ราคา  ${se_price}"
        binding_holder.tvBaht.text = "บาท"
    }

    override fun getItemCount(): Int {
        return items.size
    }
}