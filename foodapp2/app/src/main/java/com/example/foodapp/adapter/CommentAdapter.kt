package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.AdressCardDesignBinding
import com.example.foodapp.databinding.CommentCardDesignBinding
import com.example.foodapp.entity.Adress
import com.example.foodapp.entity.Comment

import com.example.foodapp.viewmodel.CommentFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class CommentAdapter(var mContext: Context,
                     var commentlist :List<Comment>,
                     var viewModel: CommentFragmentViewModel
)
    : RecyclerView.Adapter<CommentAdapter.ViewHolder>(){
    inner class ViewHolder(commentDesignBinding: CommentCardDesignBinding)
        : RecyclerView.ViewHolder(commentDesignBinding.root){
        var commentDesignBinding: CommentCardDesignBinding

        init {
            this.commentDesignBinding = commentDesignBinding
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = CommentCardDesignBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        val comment = commentlist.get(position)
        val t = holder.commentDesignBinding
        t.commentNesnesi = comment


    }

    override fun getItemCount(): Int {
        return  commentlist.size
    }
}