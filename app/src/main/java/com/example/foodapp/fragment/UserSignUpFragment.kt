package com.example.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentUserSignUpBinding
import com.example.foodapp.viewmodel.UserSignUpFragmentViewModel


class UserSignUpFragment : Fragment() {


    private lateinit var binding : FragmentUserSignUpBinding
    private lateinit var viewModel : UserSignUpFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : UserSignUpFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_sign_up, container, false)
        binding.userSignUpFragment = this




        return binding.root
    }

    fun buttonSignUp(v : View, userEmail : String, userPassword : String, userPasswordAgain : String){
        viewModel.createUser(requireActivity(), requireContext(), v, userEmail, userPassword, userPasswordAgain)
    }

    fun signIn(v : View){
        viewModel.signInFragment(v)
    }

}