package com.example.easyfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeMvvm: HomeViewModel
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()


    }

    private fun initUI() {
        initRamdomMeal()
    }

    private fun initRamdomMeal() {
        homeMvvm.getRandomMeal()
        observeRandomMeal()

    }

    private fun observeRandomMeal() {
        homeMvvm.observeRandomLiveData().observe(viewLifecycleOwner, object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                Picasso.get().load(value.strMealThumb).into(binding.ivRandommeal)
            }

        })
    }

}