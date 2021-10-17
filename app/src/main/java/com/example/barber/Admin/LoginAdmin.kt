package com.example.barber.Admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.barber.Login.LoginActivity
import com.example.barber.Register
import com.example.barber.databinding.ActivityLoginAdminBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginAdmin : AppCompatActivity() {
    private lateinit var Adminbinding : ActivityLoginAdminBinding
    var user = arrayListOf<Admin>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Adminbinding = ActivityLoginAdminBinding.inflate(layoutInflater)
        setContentView(Adminbinding.root)

        Adminbinding.goCustomer.setOnClickListener{
            var intent = Intent(this@LoginAdmin, LoginActivity::class.java)
            startActivity(intent)
        }
        Adminbinding.btnLogin.setOnClickListener {
            if (Adminbinding.edtUsername.text!!.isEmpty()  || Adminbinding.edtPassword.text!!.isEmpty()) {
                Toast.makeText(applicationContext, "Please Input Your Username", Toast.LENGTH_LONG)
                    .show()
            }else{
                login()
            }
        }
    }

    fun login() {
        val loginRequest = AdminReq()
        loginRequest.setAdmin_username(Adminbinding.edtUsername.text.toString())
        loginRequest.setAdmin_password(Adminbinding.edtPassword.text.toString())
        var loginResponseCall = AdminAPI.getUserService().AdminLogin(loginRequest)
        loginResponseCall.enqueue(object : Callback<Admin> {
            override fun onResponse(call: Call<Admin>, response: Response<Admin>) {
                if (response.isSuccessful) {
                    val sharedprefer = getSharedPreferences("Preference", Context.MODE_PRIVATE)
                    val editor = sharedprefer.edit()
                    val id = response.body()?.SF_ID.toString().toInt()
                    val name = response.body()?.SF_Name.toString()
                    val tel= response.body()?.SF_Tel.toString()
                    val address = response.body()?.SF_Address.toString()
                    val username = response.body()?.SF_Username.toString()
                    val password = response.body()?.SF_Password.toString()
                    val gender = response.body()?.SF_Gender.toString()
                    val type = response.body()?.SF_Type.toString()
                    val age = response.body()?.SF_Age.toString().toInt()
                    editor.putInt("IDKey", id)
                    editor.putString("nameKey", name)
                    editor.putString("telKey", tel)
                    editor.putString("addressKey", address)
                    editor.putString("usernameKey", username)
                    editor.putString("passwordKey", password)
                    editor.putString("genderKey", gender)
                    editor.putString("typeKey", type)
                    editor.putInt("ageKey", age)
                    editor.commit()
                    if (type=="staff"){
                        var intent= Intent(this@LoginAdmin, HomeAdmin::class.java)
                        startActivity(intent)
                    }else if(type=="owner") {
                        var intent = Intent(this@LoginAdmin, AdminOwner::class.java)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(applicationContext, "Your Username or Password Is Incorrect", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<Admin>, t: Throwable) {
                Toast.makeText(applicationContext, "error ${Adminbinding.edtUsername.text.toString()} and ${Adminbinding.edtPassword.text.toString()}", Toast.LENGTH_LONG).show()
                return t.printStackTrace()
            }
        })
    }

    fun clickSignUp(v: View){
        var intent = Intent(this@LoginAdmin, Register::class.java)
        startActivity(intent)
    }
}