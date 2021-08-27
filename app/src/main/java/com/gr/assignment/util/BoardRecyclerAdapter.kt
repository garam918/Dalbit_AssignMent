package com.gr.assignment.util

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gr.assignment.CourseMosActivity
import com.gr.assignment.R
import com.gr.assignment.RequestFragmentDialog
import com.gr.assignment.SingleTon
import com.gr.assignment.data.BoardDetailData
import com.gr.assignment.data.DefaultResponseBody
import com.gr.assignment.data.DeleteData
import com.gr.assignment.databinding.BoardItemLayoutBinding
import com.gr.assignment.network.RetrofitBuilder
import okhttp3.ResponseBody
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

            val retrofitBuilder = RetrofitBuilder

            // 게시물을 누르면 Webview Fragment로 이동하게 구현했습니다.
            binding.root.setOnClickListener {
                retrofitBuilder.networkService.getWebView(
                    SingleTon.prefs.userToken.toString()
                    ,item.contentsId.toInt()).enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        val res = response.body()!!
                        if(response.message() == "OK") {

                            it.findNavController().navigate(R.id.action_navigation_content_to_navigation_web_view,
                                bundleOf("html" to res.string()))
                        }
                    }
                    })
            }


            binding.deleteButton.setOnClickListener {
                val body = DeleteData(item.id,SingleTon.prefs.userToken.toString())
                retrofitBuilder.networkService.deleteBoard(body).enqueue(object : Callback<DefaultResponseBody>{
                    override fun onFailure(call: Call<DefaultResponseBody>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<DefaultResponseBody>,
                        response: Response<DefaultResponseBody>
                    ) {
                        val res = response.body()!!
                        // message가 삭제 완료인 경우에만 list에서 삭제되도록 구현했습니다.
                        when (res.message) {
                            "삭제완료" -> {
                                Toast.makeText(binding.root.context,"삭제가 완료되었습니다.",Toast.LENGTH_SHORT).show()
                                items.remove(item)
                            }
                            "게시물이 없습니다." -> Toast.makeText(binding.root.context,"이미 삭제된 게시물입니다.",Toast.LENGTH_SHORT).show()
                            "토큰이 만료되었습니다" -> RequestFragmentDialog().show((it.context as CourseMosActivity).supportFragmentManager,"11")
                            else -> Toast.makeText(binding.root.context,"삭제에 실패했습니다",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
}