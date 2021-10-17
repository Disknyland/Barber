package com.example.barber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Finish : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
    }

    fun btService (view : View){
        val intent = Intent(this,Home ::class.java)
        startActivity(intent)
        finish()
    }
}