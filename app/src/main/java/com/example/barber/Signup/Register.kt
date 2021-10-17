package com.example.barber

import CustomerAPI
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.barber.Login.Customer
import com.example.barber.Login.LoginActivity
import com.example.barber.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Register : AppCompatActivity() {
    private lateinit var bindingRegister: ActivityRegisterBinding
    val  createClient = CustomerAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingRegister = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bindingRegister.root)
    }
    fun clickSignUp(view: View) {
        if (bindingRegister.edtUsername.text!!.isEmpty() || bindingRegister.edtEmail.text!!.isEmpty()
            || bindingRegister.editTextName.text!!.isEmpty() || bindingRegister.editTextAge.text!!.isEmpty()
            || bindingRegister.edtTel.text!!.isEmpty()){
            Toast.makeText(applicationContext, "กรุณากรอกข้อมูลครบตามที่กำหนด", Toast.LENGTH_LONG).show()
        }else{
            var selectGender: Int = bindingRegister.radioGB.checkedRadioButtonId
            var radioButtonChecked: RadioButton = findViewById(selectGender)
            var gender_str :String = ""
            if(radioButtonChecked.text.toString()=="ชาย"){
                gender_str = "Male"
            } else if (radioButtonChecked.text.toString()=="หญิง"){
                gender_str = "Female"
            }
            createClient.signup(
                bindingRegister.edtUsername.text.toString(),
                bindingRegister.edtPassword.text.toString(),
                bindingRegister.edtEmail.text.toString(),
                bindingRegister.editTextName.text.toString(),
                bindingRegister.editTextAge.text.toString().toInt(),
                gender_str,
                bindingRegister.edtTel.text.toString()
            ).enqueue(object : Callback<Customer> {
                override fun onResponse(
                    call: Call<Customer>,
                    response: Response<Customer>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext,"ลงทะเบียนสำเร็จ", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext,"มีชื่อผู้ใช้แล้ว", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Customer>, t: Throwable) {
                    Toast.makeText(applicationContext,"ผิดพลาด onFailure " + t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
    fun clickLogin (view: View){
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}