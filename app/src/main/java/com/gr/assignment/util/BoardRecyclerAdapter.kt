package com.gr.assignment.util

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gr.assignment.SingleTon
import com.gr.assignment.data.BoardDetailData
import com.gr.assignment.data.DefaultResponseBody
import com.gr.assignment.data.DeleteData
import com.gr.assignment.databinding.BoardItemLayoutBinding
import com.gr.assignment.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            binding.deleteButton.setOnClickListener {
                val body = DeleteData(item.id,SingleTon.prefs.userToken.toString())
                Log.e("body",body.toString())
                RetrofitBuilder.networkService.deleteBoard(body).enqueue(object : Callback<DefaultResponseBody>{
                    override fun onFailure(call: Call<DefaultResponseBody>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<DefaultResponseBody>,
                        response: Response<DefaultResponseBody>
                    ) {
                        val res = response.body()!!
                        Log.e("res",res.toString())
                        when (res.message) {
                            "삭제완료" -> {
                                items.remove(item)
                            }
                            "게시물이 없습니다." -> Toast.makeText(binding.root.context,"이미 삭제된 게시물입니다.",Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(binding.root.context,"삭제에 실패했습니다",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}