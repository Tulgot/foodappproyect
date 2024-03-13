package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.pojo.Category
import com.example.easyfood.pojo.CategoryList
import com.example.easyfood.pojo.MealsByCategoryList
import com.example.easyfood.pojo.MealsByCategory
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealList
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    private var CategoryLiveData = MutableLiveData<List<Category>>()
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object: Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() !=null){
                    val randomMeal: Meal = response.body()!!.meals[0]
//                    Log.i("Test", "meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}: ")
                    randomMealLiveData.value = randomMeal
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.i("TEST", t.message.toString())
            }
        })
    }

    fun observeRandomLiveData():LiveData<Meal>{
        return randomMealLiveData
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategoryList>
        {
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                if (response.body() != null){
                    popularItemsLiveData.value = response.body()!!.meals
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.i("TEST", t.message.toString())
            }
        })
    }

    fun observePopularMealsItems(): MutableLiveData<List<MealsByCategory>>{
        return popularItemsLiveData
    }

    fun getCategories(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>
        {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null){
                    CategoryLiveData.value = response.body()!!.categories
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.i("TEST", t.message.toString())
            }

        })
    }

    fun observeCategoryItems(): MutableLiveData<List<Category>>{
        return CategoryLiveData
    }
}