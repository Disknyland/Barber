package com.example.barber.Admin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barber.databinding.ActivityHomeAdminBinding

class HomeAdmin : AppCompatActivity() {
    private  lateinit var bindingAdminHome: ActivityHomeAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingAdminHome = ActivityHomeAdminBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingAdminHome.root)
        var sharedprefer =getSharedPreferences("Preference", Context.MODE_PRIVATE)
        bindingAdminHome.userName.text =sharedprefer.getString("usernameKey2","Hello")
    }
}