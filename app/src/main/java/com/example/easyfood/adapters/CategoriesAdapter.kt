package com.example.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.databinding.CategoryItemBinding
import com.example.easyfood.pojo.Category
import com.example.easyfood.pojo.CategoryList
import com.squareup.picasso.Picasso


class CategoriesAdapter:RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var categoryList = ArrayList<Category>()

    fun setCategories(categoryList: ArrayList<Category>){
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        Picasso.get().load(categoryList[position].strCategoryThumb).into(holder.binding.ivCategory)
        holder.binding.tvCategoryName.text = categoryList[position].strCategory
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class CategoriesViewHolder(var binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root)


}