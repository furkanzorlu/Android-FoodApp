package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.CardDesignBinding
import com.example.foodapp.entity.Food
import com.example.foodapp.fragment.HomePageFragmentDirections
import com.squareup.picasso.Picasso
import java.util.*

class FoodAdapter (var mContext: Context, var foodslist: List<Food>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {


    inner class ViewHolder(foodCardBinding: CardDesignBinding) :
        RecyclerView.ViewHolder(foodCardBinding.root) {
        var foodCardBinding: CardDesignBinding


        init {
            this.foodCardBinding = foodCardBinding



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = CardDesignBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodslist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foodslist.get(position)
        val holder = holder.foodCardBinding
        holder.yemekNesnesi = food

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.food_picture_name}"
        Picasso.get().load(url).into(holder.imageViewYemekResim)

        holder.yemekCard.setOnClickListener {
            val intent = HomePageFragmentDirections.yemekDetayGecis(food)
            Navigation.findNavController(it).navigate(intent)
        }
    }
    fun filterList(filteredList: ArrayList<Food>) {
        foodslist = filteredList
        notifyDataSetChanged()
    }

     fun filter(text: String) {
         val filteredList: ArrayList<Food> = ArrayList()
        for (item in foodslist) {
            if (item.food_name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        filterList(filteredList)
    }

}