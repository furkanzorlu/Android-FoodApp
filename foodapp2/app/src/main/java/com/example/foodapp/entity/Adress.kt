package com.example.foodapp.entity

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Adress (var adress_id:String? = "",var adress_name:String? = "") : Serializable {
}