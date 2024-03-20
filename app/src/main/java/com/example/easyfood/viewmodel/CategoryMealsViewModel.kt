package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.pojo.MealsByCategory
import com.example.easyfood.pojo.MealsByCategoryList
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoryMealsViewModel:ViewModel() {

    private var mealsLiveData = MutableLiveData<List<MealsByCategory>>()

    fun getMealsByCategory(CategoryName: String){
        RetrofitInstance.api.getMealsbyCategory(CategoryName).enqueue(object : Callback<MealsByCategoryList>{
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
                response.body()?.let {
                    mealsLiveData.postValue(it.meals)
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.i("TEST", "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun observeMealsByCategory():MutableLiveData<List<MealsByCategory>>{
        return mealsLiveData
    }


}