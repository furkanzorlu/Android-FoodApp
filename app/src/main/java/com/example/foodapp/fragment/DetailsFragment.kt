package com.example.foodapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentDetailsBinding
import com.example.foodapp.entity.Food
import com.example.foodapp.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    private lateinit var binding : FragmentDetailsBinding
    private lateinit var viewModel : DetailsViewModel

    val user = Firebase.auth.currentUser

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details, container, false)


        val bundle : DetailsFragmentArgs by navArgs()
        val getFood = bundle.nesne

        binding.yemekDetayFragment = this

        binding.yemekNesnesi = getFood
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${getFood.food_picture_name}"
        Picasso.get().load(url).into(binding.imageViewDetayResim)
      binding.button4.setOnClickListener {
          val a= binding.textViewDetayYemekAd.text
          val intent = DetailsFragmentDirections.actionDetailsFragmentToCommentFragment(a.toString())
          Navigation.findNavController(it).navigate(intent)


      }

        viewModel.imageButtonAddRemove(binding)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailsViewModel by viewModels()
        viewModel = tempViewModel

    }


    fun buttonAddCart(foodObject : Food, count : String, it : View) {

            user?.let {
                val email = user.email
                viewModel.AddCart(foodObject.food_id,foodObject.food_name,foodObject.food_picture_name,
                    foodObject.food_price,count.toInt(),email.toString())

                Snackbar.make(binding.root,"The Product Has Been Successfully Added",Snackbar.LENGTH_LONG).show()
            }




    }
}
