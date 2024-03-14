package com.example.easyfood.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyfood.activities.MealActivity
import com.example.easyfood.adapters.CategoriesAdapter
import com.example.easyfood.adapters.MostPopularAdapter
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.pojo.Category
import com.example.easyfood.pojo.MealsByCategory
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularItemsAdapter: MostPopularAdapter
    private lateinit var categoriesItemAdapter: CategoriesAdapter

    companion object{
        const val MEAL_ID = "com.example.easyfood.fragments.idMeal"
        const val MEAL_NAME = "com.example.easyfood.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.easyfood.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]
        popularItemsAdapter = MostPopularAdapter()
        categoriesItemAdapter = CategoriesAdapter()
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
        preparePopularItemsRecyclerView()
        prepareCategoriesItemRecyclerView()
        initRamdomMeal()
        initPopularItems()
        initCategories()
    }

    private fun prepareCategoriesItemRecyclerView() {
        binding.rvCategories.setHasFixedSize(true)
        binding.rvCategories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = categoriesItemAdapter
    }


    private fun preparePopularItemsRecyclerView() {
        binding.rvPopularmeals.setHasFixedSize(true)
        binding.rvPopularmeals.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularmeals.adapter = popularItemsAdapter
    }
    private fun initRamdomMeal() {
        homeMvvm.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()
    }

    private fun initPopularItems() {
        homeMvvm.getPopularItems()
        observePopularItems()
        onPopularItemsClick()
    }

    private fun initCategories() {
        homeMvvm.getCategories()
        observeCategoriesLiveData()
    }

    private fun observeCategoriesLiveData() {
        homeMvvm.observeCategoryLiveData().observe(viewLifecycleOwner){
            categoriesItemAdapter.setCategories(it as ArrayList<Category>)
        }
    }


    private fun onPopularItemsClick() {
        popularItemsAdapter.onItemClick = {meal->
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observePopularItems() {
        homeMvvm.observePopularMealsItems().observe(viewLifecycleOwner
        ) { mealList->
            popularItemsAdapter.setMeals(mealList as ArrayList<MealsByCategory>)
        }
    }

    private fun onRandomMealClick() {
        binding.cvRandommeal.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandomMeal() {
        homeMvvm.observeRandomLiveData().observe(viewLifecycleOwner, object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                Picasso.get().load(value.strMealThumb).into(binding.ivRandommeal)
                randomMeal = value
            }
        })
    }

}