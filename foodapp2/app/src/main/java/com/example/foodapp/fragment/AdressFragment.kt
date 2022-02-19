package com.example.foodapp.fragment

import android.annotation.SuppressLint
import android.content.pm.PackageManager

import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.adapter.AdressAdapter
import com.example.foodapp.databinding.FragmentAdressBinding
import com.example.foodapp.viewmodel.AdressFragmentViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices

import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar

import java.util.jar.Manifest
import kotlin.math.log
import android.content.Intent





class AdressFragment : Fragment() {
    private lateinit var binding: FragmentAdressBinding
    private lateinit var adapter:AdressAdapter
    private lateinit var viewModel:AdressFragmentViewModel
    private var control = 0
    private lateinit var flpc: FusedLocationProviderClient
    private lateinit var locationTask: Task<Location>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adress, container, false)
        binding.adressFragment = this
        binding.adressToolbarTitle = "ADRESS"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar3)

        viewModel.adressList.observe(viewLifecycleOwner,{
            adapter = AdressAdapter(requireContext(),it,viewModel)
            binding.adressAdapter = adapter
        })
        flpc = LocationServices.getFusedLocationProviderClient(requireContext())
        binding.buttonSaveLocation.setOnClickListener {
            control = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)

            if (control != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    100)
            }else {
                locationTask = flpc.lastLocation
                getLocation()

            }

        }
        binding.backAdress.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_adressFragment_to_profileFragment)

        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AdressFragmentViewModel by viewModels()
        viewModel = tempViewModel


    }

    fun buttonSaveClick(adress_ad:String){
        viewModel.save(adress_ad)
        binding.editTextTextLocation.text.clear()
    }
    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(requireActivity())
        val list = geocoder.getFromLocation(lat, lng, 1)
        return list[0].getAddressLine(0)

    }
    fun getLocation(){
        locationTask.addOnSuccessListener { location ->
            if (location != null){
               val a= location.latitude
                val b= location.longitude
                val address = getAddress(a, b)
                binding.editTextTextLocation.text= Editable.Factory.getInstance().newEditable(address.toString())
            }else{


            }

        }
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == 100){
            control = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                locationTask = flpc.lastLocation
                getLocation()
            }else{

            }
        }
    }





}