package com.example.barber.Booking


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Reserve(
    @Expose
    @SerializedName("RE_ID ") val RE_ID : String,

    @Expose
    @SerializedName("Cus_ID") val Cus_ID: String,

    @Expose
    @SerializedName("SE_ID") val SE_ID: String,

    @Expose
    @SerializedName("RE_DATE") val RE_DATE: String,

    @Expose
    @SerializedName("T_ID") val T_ID: Int,

    @Expose
    @SerializedName("RE_Slip") val RE_Slip: String,

    @Expose
    @SerializedName("RE_StatusRe") val RE_StatusRe: String,

    @Expose
    @SerializedName("RE_StatusPay") val RE_StatusPay: String,
    )


{

}
