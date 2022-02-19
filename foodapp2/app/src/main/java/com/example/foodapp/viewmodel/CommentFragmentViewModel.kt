package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.databinding.FragmentCommentBinding
import com.example.foodapp.databinding.FragmentDetailsBinding
import com.example.foodapp.entity.Adress
import com.example.foodapp.entity.Comment
import com.example.foodapp.repo.AdressDaoRepository
import com.example.foodapp.repo.CommentDaoRepository
import java.lang.StringBuilder

class CommentFragmentViewModel :ViewModel() {
    var commentList = MutableLiveData<List<Comment>>()
    val krepo = CommentDaoRepository()

    init {

        commentList = krepo.getComment()
    }
    fun getComment(binding: FragmentCommentBinding){
        krepo.getAllAdress(binding)
    }
    fun save(adress_name:String,binding: FragmentCommentBinding){
        krepo.saveComment(adress_name,binding)
    }
}
