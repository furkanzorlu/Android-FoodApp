package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.databinding.FragmentHomePageBinding
import com.example.foodapp.entity.Food
import com.example.foodapp.entity.FoodCartResponse
import com.example.foodapp.repo.FoodDaoRepository

class HomapageViewModel : ViewModel() {
    var foodslist = MutableLiveData<List<Food>>()
    private val fdaor = FoodDaoRepository()

    init {
        getFoods()
        foodslist = fdaor.getFood()
    }

    fun getFoods(){
        fdaor.getAllFood()
    }

    fun homePageRvAnim(binding : FragmentHomePageBinding){
        fdaor.homePageRvAnim(binding)
    }

}