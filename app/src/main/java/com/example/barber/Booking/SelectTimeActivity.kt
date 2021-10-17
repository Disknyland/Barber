package com.example.barber.Booking

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barber.Payment
import com.example.barber.R
import com.example.barber.databinding.ActivitySelectTimeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SelectTimeActivity : AppCompatActivity() {
    private lateinit var tvdatePicker : TextView
    private lateinit var btndatePicker: Button
    private lateinit var bindingtime: ActivitySelectTimeBinding
    var serviveList = arrayListOf<Time>()
    var createClient = ServiceAPI.create()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingtime = ActivitySelectTimeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingtime.root)
        bindingtime.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        bindingtime.recyclerView.addItemDecoration(DividerItemDecoration(bindingtime.recyclerView.context,DividerItemDecoration.VERTICAL))

        tvdatePicker = findViewById(R.id.tvdate)
        btndatePicker = findViewById(R.id.datePicker)

        val cal = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(cal)
        }
        btndatePicker.setOnClickListener {
            DatePickerDialog(this, datePicker, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)
                , cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        var sharedpreferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE)
        var customer = sharedpreferences.getString("usernameKey","")
        val editor = sharedpreferences.edit()
        bindingtime.tvName.text ="ชื่อ ${customer}"
        val SeID = intent.getStringExtra("SeID")
        editor.putString("SeID", SeID)


    }

    override fun onResume() {
        super.onResume()
        callService()
    }

    fun callService(){
        serviveList.clear()
        createClient.retrievetime().enqueue(object : Callback<List<Time>> {
            override fun onResponse(call: Call<List<Time>>, response: Response<List<Time>>) {
                response.body()?.forEach { serviveList.add(Time(it.T_ID,it.T_Time))

                }

                Log.d("String :",response.body().toString())

                bindingtime.recyclerView.adapter = TimeAdapter(serviveList, applicationContext)
            }
            override fun onFailure(call: Call<List<Time>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(applicationContext,"Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateLable(cal: Calendar) {
        val format = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(format, Locale.UK)
        tvdatePicker.setText(sdf.format(cal.time))
        val datadate = bindingtime.tvdate.text.toString()
        intent.putExtra("selectdate",datadate)
        Log.d("Showdatadate : ",datadate)
        val cal = Calendar.getInstance()
    }

    fun backService(view: View) {
        val intent = Intent(this, SelectService::class.java)
        startActivity(intent)
    }
}

