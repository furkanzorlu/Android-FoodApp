package com.example.foodapp.fragment

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.foodapp.R
import com.example.foodapp.adapter.AdressAdapter

import com.example.foodapp.adapter.CommentAdapter

import com.example.foodapp.databinding.FragmentCommentBinding
import com.example.foodapp.viewmodel.AdressFragmentViewModel

import com.example.foodapp.viewmodel.CommentFragmentViewModel


class CommentFragment : Fragment() {
    private lateinit var binding: FragmentCommentBinding
    private lateinit var adapter: CommentAdapter
    private lateinit var viewModel: CommentFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comment, container, false)
        binding.commentFragment = this
        binding.commentToolbarTitle = "COMMENTS"
        val bundle : CommentFragmentArgs by navArgs()
        val getFood = bundle.foodname
            binding.textView2.text=getFood.toString()
       // binding.editTextTextLocation.text= Editable.Factory.getInstance().newEditable(getFood.toString())
        viewModel.commentList.observe(viewLifecycleOwner,{
            adapter = CommentAdapter(requireContext(),it,viewModel)
            binding.commentAdapter = adapter
        })

        viewModel.getComment(binding)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CommentFragmentViewModel by viewModels()
        viewModel = tempViewModel

    }
    fun buttonSaveClick(kisi_ad:String){
        viewModel.save(kisi_ad,binding)
    }
}