package com.example.barber.Admin

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface AdminAPI {


    @POST("loginadmin")
    fun AdminLogin(
        @Body loginReq: AdminReq
    ): Call<Admin>

    companion object{
        fun create():AdminAPI{
            val adminClient: AdminAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AdminAPI::class.java)
            return adminClient
        }
        fun getRetrofit(): Retrofit {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")  // nodeJs 10.0.2.2:3000
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit
        }
        fun getUserService(): AdminAPI {
            val userService: AdminAPI = getRetrofit().create(AdminAPI::class.java)
            return userService
        }
    }
}