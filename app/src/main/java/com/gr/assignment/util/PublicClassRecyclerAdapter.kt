package com.gr.assignment.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gr.assignment.data.PublicClassData
import com.gr.assignment.databinding.PublicClassItemLayoutBinding

class PublicClassRecyclerAdapter : RecyclerView.Adapter<PublicClassRecyclerAdapter.ViewHolder>(){
    var items = ArrayList<PublicClassData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val binding = PublicClassItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)

       return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PublicClassRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding : PublicClassItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : PublicClassData) {
            binding.item = item
        }
    }

}