package com.example.barber.Booking


import android.net.Uri
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ServiceAPI {

    @GET("allser")
    fun retrieveService(): Call<List<Service>>

    @GET("alltime")
    fun retrievetime(): Call<List<Time>>

    @FormUrlEncoded
    @POST("addreserve")
    fun reserve(
        @Field("Cus_ID") Cus_ID : String?,
        @Field("SE_ID") SE_ID : String?,
        @Field("RE_DATE") RE_DATE : String?,
        @Field("T_ID") T_ID : String?,
        @Field("RE_Slip") RE_Slip:String,
        @Field("RE_StatusRe") RE_StatusRe : String,
        @Field("RE_StatusPay") RE_StatusPay : String,
    ): Call<Reserve>




    companion object{
        fun create(): ServiceAPI{
            val serClient: ServiceAPI = Retrofit.Builder()
                .baseUrl("http://192.168.1.39:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServiceAPI::class.java)
            return serClient
        }
    }
}