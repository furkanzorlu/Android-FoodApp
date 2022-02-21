package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.FoodCartDesignBinding
import com.example.foodapp.databinding.FragmentCartBinding
import com.example.foodapp.entity.FoodCart
import com.example.foodapp.repo.FoodDaoRepository
import com.example.foodapp.viewmodel.CardViewModel
import com.squareup.picasso.Picasso

class FoodCartAdapter (var mContext : Context, var foodcardlists : List<FoodCart>, var viewModel: CardViewModel, var foodCardBinding: FragmentCartBinding)
    : RecyclerView.Adapter<FoodCartAdapter.ViewHolder>(){

    inner class ViewHolder(cardDesignBinding : FoodCartDesignBinding)
        : RecyclerView.ViewHolder(cardDesignBinding.root){
        var cardDesignBinding : FoodCartDesignBinding



        init {
            this.cardDesignBinding = cardDesignBinding

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = FoodCartDesignBinding.inflate(layoutInflater,parent,false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartFood = foodcardlists.get(position)
        val holder = holder.cardDesignBinding

        holder.yemekSepetNesnesi = cartFood

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cartFood.food_picture_name}"
        Picasso.get().load(url).into(holder.imageViewSepetYemekResim)

    }

    fun deleteCartItem(position: Int){
        val food = foodcardlists.get(position)

      viewModel.deleteFood(food.cart_food_id, food.username)


    }
    override fun getItemCount(): Int {
        return foodcardlists.size
    }
}
