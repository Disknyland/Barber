package com.example.barber.Booking

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.barber.R
import com.example.barber.databinding.ActivitySelectDateBinding
import java.text.SimpleDateFormat
import java.util.*

class SelectDateActivity : AppCompatActivity() {
    private lateinit var bindingDate : ActivitySelectDateBinding
    private lateinit var tvdatePicker : TextView
    private lateinit var btndatePicker: Button

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDate = ActivitySelectDateBinding.inflate(layoutInflater)
        setContentView(bindingDate.root)

        tvdatePicker = findViewById(R.id.tvdate)
        btndatePicker = findViewById(R.id.selectdate)

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

        bindingDate.confirmdate.setOnClickListener{
            if (bindingDate.tvdate.text.toString() == "เลือกวันที่"){
                Toast.makeText(applicationContext, "Plaease Input", Toast.LENGTH_LONG).show()
            } else {
                val date = bindingDate.tvdate.text.toString()
                val mID = intent.getStringExtra("mID")
                val mName = intent.getStringExtra("mName")
                val mPrice = intent.getStringArrayExtra("mPrice")
                val intent = Intent(this@SelectDateActivity, SelectTimeActivity::class.java)
                intent.putExtra("Date",date)
                intent.putExtra("mID",mID)
                intent.putExtra("mName",mName)
                intent.putExtra("mPrice",mPrice)
                startActivity(intent)
            }

        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateLable(cal: Calendar) {
        val format = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(format, Locale.UK)
        tvdatePicker.setText(sdf.format(cal.time))
    }
}

