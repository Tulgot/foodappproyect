package com.example.easyfood.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyfood.adapters.CategoriesAdapter
import com.example.easyfood.adapters.CategoryMealsAdapter
import com.example.easyfood.databinding.ActivityCategoryMealsBinding
import com.example.easyfood.fragments.HomeFragment
import com.example.easyfood.viewmodel.CategoryMealsViewModel

class CategoryMealsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryMealsBinding
    lateinit var categoryMealsViewModel: CategoryMealsViewModel
    lateinit var categoryMealsAdapter: CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryMealsAdapter = CategoryMealsAdapter()

        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]
        iniUI()


    }

    private fun iniUI() {
        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)
        prepareMealsItemRecyclerView()
        observeCategoryMealsViewModel()
    }

    private fun prepareMealsItemRecyclerView() {
        binding.rvMeals.setHasFixedSize(true)
        binding.rvMeals.layoutManager = GridLayoutManager(parent, 2, GridLayoutManager.VERTICAL, false)
        binding.rvMeals.adapter = categoryMealsAdapter
    }

    private fun observeCategoryMealsViewModel() {
        categoryMealsViewModel.observeMealsByCategory().observe(this, Observer{
            it.forEach{
//                Log.i("Test", it.strMeal)
               //redo
            }
        })

    }
}