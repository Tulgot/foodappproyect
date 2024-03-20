package com.example.easyfood.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.easyfood.databinding.ActivityCategoryMealsBinding
import com.example.easyfood.pojo.MealsByCategory
import com.example.easyfood.viewmodel.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryMealsBinding
    lateinit var categoryMealsViewModel: CategoryMealsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]
        iniUI()


    }

    private fun iniUI() {
        observeCategoryMealsViewModel()
    }

    private fun observeCategoryMealsViewModel() {
        categoryMealsViewModel.observeMealsByCategory().observe(this, Observer{
            it.forEach{

            }
        })

    }
}