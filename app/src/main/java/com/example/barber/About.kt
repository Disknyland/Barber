package com.example.barber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barber.Booking.SelectService
import com.example.barber.databinding.ActivityAboutBinding

class About : AppCompatActivity() {
    private lateinit var aboutBinding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(aboutBinding.root)

        aboutBinding.backHome.setOnClickListener {
            var intent = Intent(this, Home ::class.java)
            startActivity(intent)
            finish()
        }
    }
}