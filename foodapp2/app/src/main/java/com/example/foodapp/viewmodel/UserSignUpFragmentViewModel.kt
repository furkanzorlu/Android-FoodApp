package com.example.foodapp.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.foodapp.MainActivity
import com.example.foodapp.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserSignUpFragmentViewModel : ViewModel()  {

    private var auth: FirebaseAuth = Firebase.auth
    val wait = MutableLiveData<Boolean>()

    fun createUser(activity: FragmentActivity, context: Context, view: View, userEmail : String, userPassword : String, userPasswordAgain : String){
        if (userEmail.isBlank() || userPassword.isBlank() || userPasswordAgain.isBlank()){
            Snackbar.make(view,"Please fill  blank field " ,Snackbar.LENGTH_LONG).show()
        }else{
            if (userPassword != userPasswordAgain){
                Snackbar.make(view,"Please fill  blank field " ,Snackbar.LENGTH_LONG).show()
            }else{
                wait.value = true
                auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnSuccessListener {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    activity.finish()
                }.addOnFailureListener {

                    }
                }
            }

    }

    fun signInFragment(v : View){
        Navigation.findNavController(v).navigate(R.id.action_userSignUpFragment_to_userSignInFragment)
    }
}