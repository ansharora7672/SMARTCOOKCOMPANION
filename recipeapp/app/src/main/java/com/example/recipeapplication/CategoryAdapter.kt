package com.example.recipeapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapplication.databinding.CategoryRvBinding
import com.example.recipeapplication.databinding.TrytheseRvBinding

class CategoryAdapter(var dataList:ArrayList<Recipe>, var context:Context):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: CategoryRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = CategoryRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.categoryimage)
        holder.binding.tittleFood.text= dataList.get(position).tittle
        var temp = dataList.get(position).ing.split("\n").dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.timecategory.text=temp[0]


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}