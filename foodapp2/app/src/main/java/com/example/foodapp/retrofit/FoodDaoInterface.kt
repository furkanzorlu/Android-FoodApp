package com.example.foodapp.retrofit

import com.example.foodapp.entity.FoodCartResponse
import com.example.foodapp.entity.FoodResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")

    fun getAllFoods() : Call<FoodResponse>

    @POST("yemekler/sepeteYemekEkle.php")

    @FormUrlEncoded

    fun addCartFood(@Field("sepet_yemek_id") food_id : Int,
                        @Field("yemek_adi") food_name : String,
                        @Field("yemek_resim_adi") food_picture_name : String,
                        @Field("yemek_fiyat") food_price : Int,
                        @Field("yemek_siparis_adet") food_order_count : Int,
                        @Field("kullanici_adi") username : String) : Call<FoodCartResponse>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun cardFood(@Field("kullanici_adi") username: String) : Call<FoodCartResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun cardFoodDelete(@Field("sepet_yemek_id") food_cart_id : Int,
                         @Field("kullanici_adi") username: String) : Call<FoodCartResponse>
}