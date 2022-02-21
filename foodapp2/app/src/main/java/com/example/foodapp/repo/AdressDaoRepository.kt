package com.example.foodapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.foodapp.entity.Adress
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AdressDaoRepository {
    var adressList: MutableLiveData<List<Adress>>
    lateinit var refAdress: DatabaseReference
    val user = Firebase.auth.currentUser


    init {
        val db = FirebaseDatabase.getInstance()

        user?.let {
            val uid = user.uid
            refAdress = db.getReference("adress").child(uid.toString())
        }
        adressList = MutableLiveData()
    }
    fun getAdress() : MutableLiveData<List<Adress>> {
        return adressList
    }
    fun saveAdress(adress_name:String){
        val newAdress = Adress("",adress_name)
        refAdress.push().setValue(newAdress)
    }
    fun delete(adress_id:String){
        refAdress.child(adress_id).removeValue()
    }
    fun getAllAdress(){
        refAdress.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Adress>()

                for(d in snapshot.children){
                    val adress = d.getValue(Adress::class.java)

                    if(adress != null){
                        adress.adress_id = d.key
                        list.add(adress)
                    }
                }

                adressList.value = list
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}