package com.example.barber

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.barber.Booking.Reserve
import com.example.barber.Booking.SelectTimeActivity
import com.example.barber.Booking.ServiceAPI
import com.example.barber.databinding.ActivityPaymentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*

class Payment : AppCompatActivity() {
    private lateinit var BindingPayment : ActivityPaymentBinding
    lateinit var imageView: ImageView
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null
    val  createClient = ServiceAPI.create()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BindingPayment = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(BindingPayment.root)


        title = "KotlinApp"
        BindingPayment.btnUpload.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            val image = BindingPayment.paySlip.setImageURI(imageUri)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun btConfirmPayment(view: View){
        val sharedprefer = applicationContext.getSharedPreferences("MyPreference", MODE_PRIVATE)
        val Cus_id = sharedprefer.getString("IDKey", "")
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val bytes = stream.toByteArray()
        var sImage:String = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
        val RE_StatusRe = "รอการตรวจสอบ"
        val RE_StatusPay = "รอการตรวจสอบ"
        val TId = intent.getStringExtra("TId")
        val SeID = intent.getStringExtra("SeID")
        val date = intent.getStringExtra("selectdate")

        println(Cus_id)
        println(TId)
        println(date)


        createClient.reserve(Cus_id,SeID,date,TId,sImage,RE_StatusRe,RE_StatusPay)
            .enqueue(object : Callback<Reserve>{
                override fun onResponse(call: Call<Reserve>, response: Response<Reserve>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext,"ลงทะเบียนสำเร็จ", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Payment, Finish::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                override fun onFailure(call: Call<Reserve>, t: Throwable) {
                    Toast.makeText(applicationContext,"ผิดพลาด onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun backService(view: View){
        val intent = Intent(this@Payment, SelectTimeActivity::class.java)
        startActivity(intent)
    }
}

