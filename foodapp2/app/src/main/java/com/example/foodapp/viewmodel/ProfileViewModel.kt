package com.example.foodapp.viewmodel

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.InputType
import android.view.View
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.UserActivity
import com.example.foodapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase





class ProfileViewModel :ViewModel() {
    val database = Firebase.database
    val user = Firebase.auth.currentUser


    fun updateProfile(binding : FragmentProfileBinding){
        user?.let {

            val uid = user.uid

            val name1 = binding.editTextTextPersonName.text
          val tel=binding.textViewProfilePhone.text
            val profileUpdates =
                UserProfileChangeRequest.Builder().setDisplayName(name1.toString()).build()
            user?.updateProfile(profileUpdates)
            val myRef = database.getReference("user").child(uid.toString()).child("name")
            val myRef2 = database.getReference("user").child(uid.toString()).child("email")
            val myRef3 = database.getReference("user").child(uid.toString()).child("tel")
            myRef.setValue(name1.toString())
            myRef3.setValue(tel.toString().toString())
            binding.editTextTextPersonName.inputType= InputType.TYPE_NULL
            binding.textViewProfilePhone.inputType= InputType.TYPE_NULL
            binding.button3.visibility= View.GONE
            binding.lin.visibility=View.VISIBLE

        }
    }
    fun getProfile(binding : FragmentProfileBinding){
        user?.let {
            val name = user.displayName
            val email = user.email

            val uid = user.uid
            binding.editTextTextPersonName.text=Editable.Factory.getInstance().newEditable(name.toString())
            binding.textViewProfileEmail.text=Editable.Factory.getInstance().newEditable(email.toString())
            val myRef = database.getReference("user").child(uid.toString()).child("name")
            val myRef2 = database.getReference("user").child(uid.toString()).child("tel")

            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot != null) {
                        val value = snapshot.getValue<String>()

                        binding.editTextTextPersonName.text=Editable.Factory.getInstance().newEditable(value.toString())
                    myRef.setValue(value)

                    }


                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })
            myRef2.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot != null) {
                        val value = snapshot.getValue<String>()

                        binding.textViewProfilePhone.text=Editable.Factory.getInstance().newEditable(value.toString())
                        myRef2.setValue(value)

                    }


                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })

        }


    }
    fun shakeError(): TranslateAnimation? {
        val shake = TranslateAnimation(0F, 10F, 0F, 0F)
        shake.duration = 500
        shake.interpolator = CycleInterpolator(7F)
        return shake
    }
    fun quit(activity : FragmentActivity, context : Context){
        val intent = Intent(context, UserActivity::class.java)
        context.startActivity(intent)
        activity.finish()
    }
    fun buttonUpdateClick(binding: FragmentProfileBinding){
        binding.editTextTextPersonName.startAnimation(shakeError())
        binding.textViewProfilePhone.startAnimation(shakeError())
        binding.editTextTextPersonName.inputType= InputType.TYPE_CLASS_TEXT
        binding.editTextTextPersonName.requestFocus()
        binding.textViewProfilePhone.inputType= InputType.TYPE_CLASS_NUMBER
        binding.button3.visibility=View.VISIBLE
        binding.lin.visibility=View.INVISIBLE
    }
    fun intentAdress(v : View){
        Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_adressFragment)
    }
}