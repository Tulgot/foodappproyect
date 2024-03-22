package com.example.easyfood.db

import androidx.room.Database
import com.example.easyfood.pojo.Meal

@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class mealDatabase {

    abstract fun mealDao():MealDao

    companion object{

    }

}