package com.gr.assignment.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gr.assignment.R
import com.gr.assignment.data.CourseInfoData
import com.gr.assignment.databinding.CourseItemLayoutBinding

class LectureRecyclerAdapter : RecyclerView.Adapter<LectureRecyclerAdapter.ViewHolder>() {
    var items = ArrayList<CourseInfoData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = CourseItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding : CourseItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : CourseInfoData) {
            binding.item = item

            binding.root.setOnClickListener {
                binding.root.findNavController().navigate(R.id.action_navigation_home_to_navigation_view_pager)
            }
        }
    }
}