package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.databinding.CardDesignBinding
import com.example.foodapp.databinding.FoodCartDesignBinding
import com.example.foodapp.databinding.FragmentCartBinding
import com.example.foodapp.databinding.FragmentDetailsBinding
import com.example.foodapp.entity.FoodCart
import com.example.foodapp.repo.FoodDaoRepository

class CardViewModel : ViewModel() {
    var foodCartList : MutableLiveData<List<FoodCart>>
    private val fdaor = FoodDaoRepository()

    init {
        showCartFood()
        foodCartList = fdaor.getCartFood()
    }

    fun showCartFood() {
        fdaor.getAllCartFood()
    }

    fun deleteFood(cart_food_id : Int, username : String){
        fdaor.cardDeleteFood(cart_food_id,username)
    }

    fun foodCartAnim(binding : FragmentCartBinding){
       fdaor.foodCartAnim(binding)
    }



}