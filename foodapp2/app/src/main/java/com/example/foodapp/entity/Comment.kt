package com.example.foodapp.entity

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class Comment (var comment_id:String? = "",var comment_name:String? = "",var comment_user_name:String? = "") : Serializable {
}