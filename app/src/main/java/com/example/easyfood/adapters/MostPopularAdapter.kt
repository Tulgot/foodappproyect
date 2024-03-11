package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.databinding.PopularItemsBinding
import com.example.easyfood.pojo.CategoryMeals
import com.squareup.picasso.Picasso

class MostPopularAdapter : RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {

    private var mealsList = ArrayList<CategoryMeals>()

    fun setMeals(mealsList: ArrayList<CategoryMeals>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(
            PopularItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Picasso.get().load(mealsList[position].strMealThumb).into(holder.binding.ivPopularMeals)
    }

    class PopularMealViewHolder(var binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

}