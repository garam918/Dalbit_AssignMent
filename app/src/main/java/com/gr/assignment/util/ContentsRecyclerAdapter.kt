package com.gr.assignment.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gr.assignment.data.CourseDetailData
import com.gr.assignment.databinding.CourseContentItemLayoutBinding

class ContentsRecyclerAdapter : RecyclerView.Adapter<ContentsRecyclerAdapter.ViewHolder>() {
    var items = ArrayList<CourseDetailData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = CourseContentItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ContentsRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding : CourseContentItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : CourseDetailData){
            binding.item = item

            binding.root.setOnClickListener {

            }
        }
    }
}