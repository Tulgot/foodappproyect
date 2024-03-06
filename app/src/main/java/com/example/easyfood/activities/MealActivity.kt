package com.example.easyfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.fragments.HomeFragment
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewmodel.MealViewModel
import com.squareup.picasso.Picasso

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealid: String
    private lateinit var mealname: String
    private lateinit var mealthumb: String
    private lateinit var MealMVVM: MealViewModel
    private lateinit var MealDetail: Meal


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MealMVVM = ViewModelProvider(this)[MealViewModel::class.java]
        initUI()

    }

    private fun initUI() {
        getMealInfoFromIntent()
        showMealInfo()
        MealMVVM.getMealDetail(mealid)
        observeMealDetail()
    }

    private fun observeMealDetail() {
        MealMVVM.observeMealDetailLiveData().observe(this, object : Observer<Meal> {
            override fun onChanged(value: Meal) {
                binding.tvLocation.text = "Area: ${value.strArea}"
                binding.tvCategory.text = "Category: ${value.strCategory}"
                binding.tvDetailInstructions.text = value.strInstructions
            }
        })
    }

    private fun getMealInfoFromIntent() {
        val intent = intent
        mealid = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealname = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealthumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun showMealInfo() {
        binding.tvMealtitle.text = mealname
        Picasso.get().load(mealthumb).into(binding.ivMealdetail)
    }
}