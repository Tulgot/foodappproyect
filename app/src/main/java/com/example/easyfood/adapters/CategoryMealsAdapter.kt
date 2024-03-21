package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.databinding.CategoryItemBinding
import com.example.easyfood.databinding.MealItemBinding
import com.example.easyfood.pojo.MealsByCategory
import com.squareup.picasso.Picasso

class CategoryMealsAdapter: RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewModel>(){

    private var mealsList = ArrayList<MealsByCategory>()

    fun setMealList(mealsList : List<MealsByCategory>){
        this.mealsList = mealsList as ArrayList<MealsByCategory>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryMealsAdapter.CategoryMealsViewModel {
        return CategoryMealsViewModel(MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: CategoryMealsAdapter.CategoryMealsViewModel,
        position: Int
    ) {
        var category = mealsList[position].strMealThumb
        Picasso.get().load(category).into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = category
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    inner class CategoryMealsViewModel(var binding : MealItemBinding): RecyclerView.ViewHolder(binding.root)
}