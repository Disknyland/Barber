package com.example.barber.Admin

import android.media.Image
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Admin(
        @Expose
        @SerializedName("SF_ID ") val SF_ID : Int,

        @Expose
        @SerializedName("SF_Name") val SF_Name: String,

        @Expose
        @SerializedName("SF_Tel") val SF_Tel: String,

        @Expose
        @SerializedName("SF_Address") val SF_Address: String,

        @Expose
        @SerializedName("SF_Username") val SF_Username: String,

        @Expose
        @SerializedName("SF_Password") val SF_Password: String,

        @Expose
        @SerializedName("SF_Gender") val SF_Gender: String,

        @Expose
        @SerializedName("SF_Type") val SF_Type: String,

        @Expose
        @SerializedName("SF_Age") val SF_Age: String,

        @Expose
        @SerializedName("SF_Image") val SF_Image: Image,
        )


    {

    }