package com.example.barber.Booking

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barber.Payment
import com.example.barber.databinding.ShowtimeLayoutBinding


class TimeAdapter (val items : List<Time>,val context : Context):
    RecyclerView.Adapter<TimeAdapter.ViewHolder>(){
    inner class ViewHolder(view: View, val binding : ShowtimeLayoutBinding) : RecyclerView.ViewHolder(view) {
        init {
            binding.select.setOnClickListener{
                val item = items[adapterPosition]
                val context_v: Context = view.context
                val intent = Intent(context_v, Payment::class.java)

                intent.getStringExtra("SeName")
                var SeID = intent.getStringExtra("SeID")
                intent.putExtra("", SeID)
                var selectdate = intent.getStringExtra("selectdate")
                intent.putExtra("TId", item.T_ID)
                intent.putExtra("TTime", item.T_Time)
                intent.putExtra("selectdate",selectdate)
                intent.putExtra("SeID", SeID)


                Log.d("TimeInfo : ",item.toString())
                context_v.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShowtimeLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding_holder = holder.binding
        var T_Time = items[position].T_Time
        var T_ID = items[position].T_ID
        binding_holder.tvTime.text = "${T_ID} : ${T_Time}"
    }

    override fun getItemCount(): Int {
        return items.size
    }
}