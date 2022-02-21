package com.example.foodapp.fragment

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentUserSignInBinding
import com.example.foodapp.viewmodel.UserSignInFragmentViewModel


class UserSignInFragment : Fragment() {
    private lateinit var binding : FragmentUserSignInBinding
    private lateinit var viewModel : UserSignInFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : UserSignInFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_user_sign_in, container, false)
        binding.userSignInFragment = this



        return binding.root
    }

    fun userSignIn(v : View, userEmail : String, userPassword : String){
        viewModel.signIn(requireActivity(), requireContext(), v, userEmail, userPassword)
    }

    fun signUp(v : View){
        viewModel.signUp(v)
    }
}