package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.pojo.CategoryList
import com.example.easyfood.pojo.CategoryMeals
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealList
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel():ViewModel() {

    private var mealDetailsLiveData =  MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<CategoryMeals>>()

    fun getMealDetail(id:String){
        RetrofitInstance.api.getMealDetail(id).enqueue(object:  Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() !=null){
                    val mealDetail: Meal = response.body()!!.meals[0]
//                    Log.i("Test", "meal id ${randomMeal.idMeal} name ${randomMeal.strMeal}: ")
                    mealDetailsLiveData.value = mealDetail
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.i("TEST", t.message.toString())
            }
        })
    }

    fun observeMealDetailLiveData(): MutableLiveData<Meal>{
        return mealDetailsLiveData
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<CategoryList>
        {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null){
                    popularItemsLiveData.value = response.body()!!.meals
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.i("TEST", t.message.toString())
            }
        })
    }

    fun observePopularMealsItems(): MutableLiveData<List<CategoryMeals>>{
        return popularItemsLiveData
    }
}