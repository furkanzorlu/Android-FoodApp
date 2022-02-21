package com.example.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.foodapp.R
import com.example.foodapp.adapter.FoodCartAdapter
import com.example.foodapp.databinding.FoodCartDesignBinding
import com.example.foodapp.databinding.FragmentCartBinding
import com.example.foodapp.entity.SwipeToDelete
import com.example.foodapp.viewmodel.CardViewModel
import com.google.android.material.snackbar.Snackbar


class CartFragment : Fragment() {

    private lateinit var binding : FragmentCartBinding
    private lateinit var viewModel : CardViewModel
    private lateinit var adapter : FoodCartAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)
        binding.fragmentYemekSepet = this
        binding.cartToolbarTitle = "MY BASKET"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        viewModel.foodCartAnim(binding)

        viewModel.foodCartList.observe(viewLifecycleOwner, { foodCartList ->
            var result = 0
            foodCartList.map {
                result += it.food_price * it.food_order_count

            }

            binding.buttonConfirm.setOnClickListener {
                Snackbar.make(binding.root,"Your order has been received.", Snackbar.LENGTH_LONG).show()


            }

            binding.textViewCardTotalPrice.text = "${result.toString()} ₺"

            adapter = FoodCartAdapter(requireContext(),foodCartList, viewModel,binding)

            binding.foodCartAdapter = adapter

            ItemTouchHelper(SwipeToDelete(adapter)).attachToRecyclerView(binding.recyclerView)
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : CardViewModel by viewModels()
        viewModel = tempViewModel

    }

}