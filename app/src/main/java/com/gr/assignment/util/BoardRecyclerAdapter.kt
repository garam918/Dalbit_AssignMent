package com.gr.assignment.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gr.assignment.data.BoardDetailData
import com.gr.assignment.databinding.BoardItemLayoutBinding

class BoardRecyclerAdapter : RecyclerView.Adapter<BoardRecyclerAdapter.ViewHolder>() {
    var items = ArrayList<BoardDetailData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = BoardItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BoardRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding : BoardItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BoardDetailData) {
            binding.item = item


        }
    }
}