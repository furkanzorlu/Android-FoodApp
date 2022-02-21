package com.example.foodapp.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.foodapp.MainActivity
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentUserSignInBinding
import com.example.foodapp.fragment.HomePageFragment
import com.example.foodapp.fragment.HomePageFragmentDirections

import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserSignInFragmentViewModel :ViewModel() {
    private var auth : FirebaseAuth = Firebase.auth


    fun signIn(activity : FragmentActivity, context : Context, view: View, userEmail : String, userPassword : String){
        if (userEmail.isBlank() || userPassword.isBlank()){
            Snackbar.make(view,"Please fill  blank field " ,Snackbar.LENGTH_LONG).show()
        }
      else {
            auth.signInWithEmailAndPassword(userEmail, userPassword).addOnSuccessListener {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                activity.finish()

            }.addOnFailureListener {
                Snackbar.make(view,"error " ,Snackbar.LENGTH_LONG).show()

            }
        }



        }


    fun signUp(v : View){
        Navigation.findNavController(v).navigate(R.id.action_userSignInFragment_to_userSignUpFragment)
    }
}