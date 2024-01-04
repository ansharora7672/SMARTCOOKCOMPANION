package com.example.recipeapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapplication.databinding.TrytheseRvBinding

class TryAdapter(var dataList:ArrayList<Recipe>,var context: Context): RecyclerView.Adapter<TryAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: TrytheseRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = TrytheseRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(dataList.get(position).img).into(holder.binding.try1Img)
        holder.binding.try1Text.text = dataList.get(position).tittle
        var time = dataList.get(position).ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.TIME.text = time.get(0)
    }
}