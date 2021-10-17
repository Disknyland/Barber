package com.example.barber.Booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Service(
    @Expose
    @SerializedName("SE_ID") val SE_ID: Int,

    @Expose
    @SerializedName("SE_Name") val SE_Name: String,

    @Expose
    @SerializedName("SE_Price") val SE_Price: Int,

    @Expose
    @SerializedName("Store_ID") val Store_ID: Int
){}
