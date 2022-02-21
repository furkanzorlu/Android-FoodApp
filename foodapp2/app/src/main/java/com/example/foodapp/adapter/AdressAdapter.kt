package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.AdressCardDesignBinding
import com.example.foodapp.entity.Adress
import com.example.foodapp.viewmodel.AdressFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class AdressAdapter (var mContext: Context,
                     var adresslist :List<Adress>,
                     var viewModel: AdressFragmentViewModel)
           : RecyclerView.Adapter<AdressAdapter.ViewHolder>(){
    inner class ViewHolder(adressDesignBinding: AdressCardDesignBinding)
        : RecyclerView.ViewHolder(adressDesignBinding.root){
        var adressDesignBinding: AdressCardDesignBinding

        init {
            this.adressDesignBinding = adressDesignBinding
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdressAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = AdressCardDesignBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdressAdapter.ViewHolder, position: Int) {
        val adress = adresslist.get(position)
        val t = holder.adressDesignBinding
        t.adressNesnesi = adress
        t.imageViewDeletePicture.setOnClickListener {
            Snackbar.make(it,"${adress.adress_name} Delete?", Snackbar.LENGTH_LONG)
                .setAction("YES"){
                    viewModel.delete(adress.adress_id!!)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return  adresslist.size
    }
}