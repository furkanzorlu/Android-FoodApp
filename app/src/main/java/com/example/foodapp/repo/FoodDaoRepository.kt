package com.example.foodapp.repo

import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.R
import com.example.foodapp.databinding.FoodCartDesignBinding
import com.example.foodapp.databinding.FragmentCartBinding
import com.example.foodapp.databinding.FragmentDetailsBinding
import com.example.foodapp.databinding.FragmentHomePageBinding
import com.example.foodapp.entity.Food
import com.example.foodapp.entity.FoodCart
import com.example.foodapp.entity.FoodCartResponse
import com.example.foodapp.entity.FoodResponse
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.retrofit.FoodDaoInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDaoRepository{
private val foodList : MutableLiveData<List<Food>>
private val foodDaoInterface : FoodDaoInterface
private val foodCartList : MutableLiveData<List<FoodCart>>
    val user = Firebase.auth.currentUser
init {
    foodDaoInterface = ApiUtils.getFoodDaoInterface()
    foodList = MutableLiveData()
    foodCartList = MutableLiveData()
}

fun getFood() : MutableLiveData<List<Food>> {
    return foodList
}

fun getAllFood() {

    foodDaoInterface.getAllFoods().enqueue(object : Callback<FoodResponse> {
        override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
            val list = response.body().foods_list
            foodList.value = list

        }

        override fun onFailure(call: Call<FoodResponse>, t: Throwable) {}
    })
}

fun getCartFood() : MutableLiveData<List<FoodCart>> {
    return  foodCartList
}

fun getAllCartFood() {
    user?.let {
        val email = user.email

        foodDaoInterface.cardFood(email.toString()).enqueue(object :
            Callback<FoodCartResponse> {
            override fun onResponse(
                call: Call<FoodCartResponse>, response: Response<FoodCartResponse>
            ) {
                val list = response.body().cart_food
                foodCartList.value = list
            }

            override fun onFailure(call: Call<FoodCartResponse>, t: Throwable) {}
        })
    }
}
fun addCart(food_cart_id : Int,
               food_name : String,
               food_picture_name : String,
               food_price : Int,
               food_order_count : Int,
               username : String) {
    foodDaoInterface.addCartFood(food_cart_id,food_name,food_picture_name,food_price,food_order_count,username).enqueue(object :
        Callback<FoodCartResponse> {
        override fun onResponse(call: Call<FoodCartResponse>, response: Response<FoodCartResponse>) {
            if (response.isSuccessful) {
                if (response.body().success == 1){

                }
            }
        }
        override fun onFailure(call: Call<FoodCartResponse>, t: Throwable) {}
    })
}

private fun displayFoodCount(number : Int, binding : FragmentDetailsBinding) {
    binding.textViewNumber.text = "$number"

}
    private fun displayTotalPrice(number : Int, binding : FragmentDetailsBinding){
        binding.textViewTotalPrice.text="$number"
    }

    fun increaseInteger(binding : FragmentDetailsBinding) {
        displayFoodCount(binding.textViewNumber.text.toString().toInt() + 1,binding)
        displayTotalPrice(binding.textViewNumber.text.toString().toInt() * binding.textViewPrice.text.toString().toInt(),binding)
}

    fun decreaseInteger(binding : FragmentDetailsBinding) {
        displayFoodCount(binding.textViewNumber.text.toString().toInt() - 1,binding)
        displayTotalPrice(binding.textViewNumber.text.toString().toInt() * binding.textViewPrice.text.toString().toInt(),binding)
        if (binding.textViewNumber.text == "0"){
            binding.textViewNumber.text = "1"
        }
    }
fun addRemoveButton(binding: FragmentDetailsBinding){
    binding.imageButtonAdd.setOnClickListener { increaseInteger(binding) }
    binding.imageButtonRemove.setOnClickListener {
        decreaseInteger(binding)}

}
    fun cardDeleteFood(yemek_id : Int, kullanici_adi: String){
        foodDaoInterface.cardFoodDelete(yemek_id, kullanici_adi).enqueue(object :
            Callback<FoodCartResponse> {
            override fun onResponse(call: Call<FoodCartResponse>, response: Response<FoodCartResponse>) {
                getAllCartFood()
            }
            override fun onFailure(call: Call<FoodCartResponse>, t: Throwable) {}
        })
    }
    fun foodCartAnim(binding : FragmentCartBinding) {
        val rvanim = AnimationUtils.loadAnimation(
            binding.recyclerView.context,
            R.anim.recyclerview_animation
        )
        binding.recyclerView.startAnimation(rvanim)
    }
        fun homePageRvAnim(binding : FragmentHomePageBinding){
            val rvanim = AnimationUtils.loadAnimation(binding.foodRecyclerView.context, R.anim.recyclerview_animation)
            binding.foodRecyclerView.startAnimation(rvanim)
        }



}