package com.example.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodapp.databinding.CardDesignBinding
import com.example.foodapp.databinding.FoodCartDesignBinding
import com.example.foodapp.databinding.FragmentDetailsBinding
import com.example.foodapp.repo.FoodDaoRepository

class DetailsViewModel : ViewModel() {
    private var fdaor : FoodDaoRepository

    init {
        fdaor = FoodDaoRepository()
    }

    fun AddCart(food_id : Int,
                  food_name : String,
                  food_picture_name : String,
                  food_price : Int,
                  food_order_count : Int,
                  username : String){
        fdaor.addCart(food_id,food_name,food_picture_name,food_price,food_order_count,username)
    }



    fun imageButtonAddRemove(tasarim: FragmentDetailsBinding){
        fdaor.addRemoveButton(tasarim)
    }





}