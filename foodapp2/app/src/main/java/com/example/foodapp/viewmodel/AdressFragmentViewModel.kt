package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.entity.Adress
import com.example.foodapp.repo.AdressDaoRepository

class AdressFragmentViewModel: ViewModel() {
    var adressList = MutableLiveData<List<Adress>>()
    val krepo = AdressDaoRepository()
    init {
        getAdresss()
        adressList = krepo.getAdress()
    }
    fun getAdresss(){
        krepo.getAllAdress()
    }
    fun save(adress_name:String){
        krepo.saveAdress(adress_name)
    }
    fun delete(adress_id: String){
        krepo.delete(adress_id)
    }
}