package com.example.easyfood.retrofit

import com.example.easyfood.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("/api/json/v1/1/random.php")
    fun getRandomMeal():Call<MealList>
}