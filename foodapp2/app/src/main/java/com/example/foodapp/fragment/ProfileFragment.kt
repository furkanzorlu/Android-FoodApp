package com.example.foodapp.fragment

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentProfileBinding
import com.example.foodapp.viewmodel.ProfileViewModel
import com.example.foodapp.viewmodel.UserSignUpFragmentViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase
import android.view.animation.CycleInterpolator

import android.view.animation.TranslateAnimation







class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : ProfileViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        binding.profile = this
        binding.profileToolbarTitle="PROFILE"
        binding.editTextTextPersonName.inputType= InputType.TYPE_NULL
        binding.textViewProfileEmail.inputType= InputType.TYPE_NULL
        binding.textViewProfilePhone.inputType= InputType.TYPE_NULL

        viewModel.getProfile(binding)

        return binding.root
    }

    fun quit(){
        viewModel.quit(requireActivity(),requireContext())
    }
    fun update(){
        viewModel.updateProfile(binding)
    }
     fun buttonUpdateClick(){
         viewModel.buttonUpdateClick(binding)
     }
    fun intentAdress(v : View){
       viewModel.intentAdress(v)
    }


}