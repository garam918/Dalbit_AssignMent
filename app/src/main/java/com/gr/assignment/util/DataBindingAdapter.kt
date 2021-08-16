package com.gr.assignment.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gr.assignment.R
import com.gr.assignment.data.LectureData
import com.gr.assignment.data.PublicClassData
import de.hdodenhof.circleimageview.CircleImageView

object DataBindingAdapter {

    // RecyclerView의 Item을 BindingAdapter를 이용해 Binding 시켰습니다.
    @BindingAdapter("lectureItem")
    @JvmStatic
    fun lectureBinding(recyclerView: RecyclerView, items : ObservableArrayList<LectureData>) {
        if(recyclerView.adapter == null) {
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = LectureRecyclerAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as LectureRecyclerAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()
    }

    @BindingAdapter("publicClassItem")
    @JvmStatic
    fun classBinding(recyclerView: RecyclerView, items: ObservableArrayList<PublicClassData>) {
        if(recyclerView.adapter == null) {
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = PublicClassRecyclerAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as PublicClassRecyclerAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()
    }

    // Glide 라이브러리를 이용해 실제 프로필 이미지가 저장된 url의 주소를 넣으면 imageView에 원형으로 표시되게 구현했습니다.
    @BindingAdapter("setProfileImage")
    @JvmStatic
    fun setProfile(imageView: CircleImageView, url : String) {
        Glide.with(imageView.context).load(url).error(R.drawable.profileColor).into(imageView)
    }

    // count의 값이 0인 경우 textView를 보이지 않게 설정했습니다.
    @BindingAdapter("setVisible")
    @JvmStatic
    fun setVisible(textView: TextView, count : Int) {
        if(count == 0) textView.visibility = View.GONE
        else textView.visibility = View.VISIBLE
    }
}