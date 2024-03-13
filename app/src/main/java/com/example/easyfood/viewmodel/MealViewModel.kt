package com.example.easyfood.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealList
import com.example.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel():ViewModel() {

    private var mealDetailsLiveData =  MutableLiveData<Meal>()

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


}