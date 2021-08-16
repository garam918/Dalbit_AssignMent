package com.gr.assignment.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gr.assignment.data.LectureData
import com.gr.assignment.databinding.LectureItemLayoutBinding

class LectureRecyclerAdapter : RecyclerView.Adapter<LectureRecyclerAdapter.ViewHolder>() {
    var items = ArrayList<LectureData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = LectureItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding : LectureItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : LectureData) {
            binding.item = item
        }
    }
}