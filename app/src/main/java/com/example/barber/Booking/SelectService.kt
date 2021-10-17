package com.example.barber.Booking

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barber.Home
import com.example.barber.databinding.ActivitySelectServiceBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectService : AppCompatActivity() {
    private lateinit var serviceBinding : ActivitySelectServiceBinding
    var serviveList = arrayListOf<Service>()
    var createClient = ServiceAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceBinding = ActivitySelectServiceBinding.inflate(layoutInflater)
        setContentView(serviceBinding.root)
        serviceBinding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        serviceBinding.recyclerView.addItemDecoration(DividerItemDecoration(serviceBinding.recyclerView.context,DividerItemDecoration.VERTICAL))

        serviceBinding.backHome.setOnClickListener{
            var intent = Intent(this,Home ::class.java)
            startActivity(intent)
        }
        var sharedpreferences =getSharedPreferences("MyPreference", Context.MODE_PRIVATE)
        var customer = sharedpreferences.getString("usernameKey","")
        serviceBinding.tvName.text ="ชื่อ ${customer}"

    }

    override fun onResume() {
        super.onResume()
        callService()
    }

    fun callService(){
        serviveList.clear()
        createClient.retrieveService().enqueue(object : Callback<List<Service>>{
            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
                response.body()?.forEach {
                    serviveList.add(Service(it.SE_ID,it.SE_Name,it.SE_Price,it.Store_ID))
                }
                serviceBinding.recyclerView.adapter = SelectServiceAdapter(serviveList, applicationContext)
            }
            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(applicationContext,"Error", Toast.LENGTH_LONG).show()
            }
        })
    }
}