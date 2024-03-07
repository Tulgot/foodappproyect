package com.example.easyfood.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.databinding.PopularItemsBinding

class MostPopularAdapter: RecyclerView.Adapter<MostPopularAdapter.PopularMealViewModel>() {

    class PopularMealViewModel(private val binding:PopularItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewModel {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PopularMealViewModel, position: Int) {
        TODO("Not yet implemented")
    }
}