package com.example.barber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barber.Admin.LoginAdmin
import com.example.barber.Booking.SelectService
import com.example.barber.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var homeBinding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        homeBinding.btnreserve.setOnClickListener{
            var intent = Intent(this@Home,SelectService ::class.java)
            startActivity(intent)
            finish()
        }

        homeBinding.clickAbout.setOnClickListener {
            var intent = Intent(this@Home, About::class.java)
            startActivity(intent)
            finish()
        }
    }
}