package com.example.vivekbhalodiya.railticket.api.model.TrainFare

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Quota(@SerializedName("code")
                 val code: String = "",
                 @SerializedName("name")
                 val name: String = ""): Serializable