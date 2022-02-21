package com.example.foodapp.repo

import androidx.lifecycle.MutableLiveData
import com.example.foodapp.adapter.FoodAdapter
import com.example.foodapp.adapter.FoodCartAdapter
import com.example.foodapp.databinding.FragmentCommentBinding
import com.example.foodapp.databinding.FragmentDetailsBinding
import com.example.foodapp.entity.Adress
import com.example.foodapp.entity.Comment
import com.example.foodapp.entity.Food
import com.example.foodapp.viewmodel.DetailsViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class CommentDaoRepository {
    var commentlist: MutableLiveData<List<Comment>>
    lateinit var refComment: DatabaseReference
    val user = Firebase.auth.currentUser



    init {

        commentlist = MutableLiveData()
    }

    fun getComment() : MutableLiveData<List<Comment>> {
        return commentlist
    }
    fun saveComment(comment_name:String,binding: FragmentCommentBinding){
        user?.let {

            val name=user.displayName
            val newComment = Comment("",comment_name,name.toString())
            val text=  binding.textView2.text.toString()

            val db = FirebaseDatabase.getInstance()
            refComment = db.getReference("comment").child(text)
            refComment.push().setValue(newComment)



        }


    }
    fun getAllAdress(binding: FragmentCommentBinding){
        user?.let {

            val text=  binding.textView2.text.toString()
            val db = FirebaseDatabase.getInstance()
            refComment = db.getReference("comment").child(text)
            refComment.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = ArrayList<Comment>()

                    for(d in snapshot.children){
                        val comment = d.getValue(Comment::class.java)

                        if(comment != null){
                            comment.comment_id = d.key
                            list.add(comment)
                        }
                    }

                    commentlist.value = list
                }
                override fun onCancelled(error: DatabaseError) {}
            })
        }

    }
}
