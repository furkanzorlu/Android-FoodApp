package com.example.foodapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodapp.R
import com.example.foodapp.adapter.FoodAdapter
import com.example.foodapp.databinding.FragmentHomePageBinding
import com.example.foodapp.entity.Food
import com.example.foodapp.viewmodel.HomapageViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.text.Editable

import android.text.TextWatcher
import androidx.core.widget.doOnTextChanged
import java.util.*


class HomePageFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding : FragmentHomePageBinding
    private lateinit var adapter : FoodAdapter
    private lateinit var viewModel : HomapageViewModel
    val user = Firebase.auth.currentUser

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page, container, false)

        user?.let {

            val name=user.displayName

            binding.homepageToolbarTitle = "Hello" + " " +name.toString()



        }
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        viewModel.homePageRvAnim(binding)


        viewModel.foodslist.observe(viewLifecycleOwner,{ foodslist ->
            adapter = FoodAdapter(requireContext(), foodslist )
            binding.foodsAdapter = adapter

        })


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:HomapageViewModel by viewModels()
        this.viewModel = tempViewModel

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)


        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onQueryTextSubmit(query: String): Boolean {

        adapter.filter(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {


        Log.e("dene",newText)

        adapter.filter(newText)

       return true
    }




}

