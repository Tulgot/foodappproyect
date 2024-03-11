package com.example.easyfood.retrofit

import com.example.easyfood.pojo.CategoryList
import com.example.easyfood.pojo.CategoryMeals
import com.example.easyfood.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("/api/json/v1/1/random.php")
    fun getRandomMeal():Call<MealList>

    @GET("api/json/v1/1/lookup.php?")
    fun getMealDetail(@Query("i") id:String): Call<MealList>

    @GET("/api/json/v1/1/filter.php?")
    fun getPopularItems(@Query("c") categoryName: String):Call<CategoryList>
}