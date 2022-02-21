package com.example.foodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodCartResponse (@SerializedName("sepet_yemekler")
                        @Expose
                        var cart_food : List<FoodCart>,
                        @SerializedName("success")
                        @Expose
                        var success : Int) {
}