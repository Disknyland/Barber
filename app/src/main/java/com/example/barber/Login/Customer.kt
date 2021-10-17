package com.example.barber.Login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Customer(

    @Expose
    @SerializedName("Cus_ID") val Cus_ID : String,

    @Expose
    @SerializedName("Cus_Username") val Cus_Username : String,

    @Expose
    @SerializedName("Cus_Password") val Cus_Password: String,

    @Expose
    @SerializedName("Cus_Email") val Cus_Email: String,

    @Expose
    @SerializedName("Cus_Name") val Cus_Name: String,

    @Expose
    @SerializedName("Cus_Age") val Cus_Age: Int,

    @Expose
    @SerializedName("Cus_Gender") val Cus_Gender: String,

    @Expose
    @SerializedName("Cus_Tel") val Cus_Tel: String,)
{
}