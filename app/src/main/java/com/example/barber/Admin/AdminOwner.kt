package com.example.barber.Admin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.barber.databinding.ActivityAdminOwnerBinding
import com.example.barber.databinding.ActivityHomeAdminBinding

class AdminOwner : AppCompatActivity() {
    private  lateinit var bindingAdminOwner: ActivityAdminOwnerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingAdminOwner = ActivityAdminOwnerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingAdminOwner.root)
        var sharedprefer =getSharedPreferences("Preference", Context.MODE_PRIVATE)
        bindingAdminOwner.Adminowner.text =sharedprefer.getString("usernameKey2","Hello Owner")
    }
}