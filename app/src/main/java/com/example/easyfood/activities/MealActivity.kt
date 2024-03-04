package com.example.easyfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.fragments.HomeFragment

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealid: String
    private lateinit var mealname: String
    private lateinit var mealthumb: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMealInfoFromIntent()
    }

    private fun getMealInfoFromIntent() {
        val intent = intent
        mealid = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealname = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealthumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
}