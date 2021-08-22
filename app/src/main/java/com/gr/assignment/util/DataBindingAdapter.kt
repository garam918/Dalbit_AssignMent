package com.gr.assignment.util

import android.content.Intent
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gr.assignment.CourseMosActivity
import com.gr.assignment.R
import com.gr.assignment.SingleTon
import com.gr.assignment.data.CourseInfoData
import com.gr.assignment.data.PublicClassData
import com.gr.assignment.data.ResponseLoginData
import com.gr.assignment.network.RetrofitBuilder
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataBindingAdapter {

    // RecyclerView의 Item을 BindingAdapter를 이용해 Binding 시켰습니다.
    @BindingAdapter("lectureItem")
    @JvmStatic
    fun lectureBinding(recyclerView: RecyclerView, items : ObservableArrayList<CourseInfoData>) {
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

    @BindingAdapter("setSpinner")
    @JvmStatic
    fun setSpinner(spinner: Spinner, items: MutableLiveData<ArrayList<String>>) {
        val adapter = ArrayAdapter(spinner.context,android.R.layout.simple_spinner_item,items.value!!)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
    }

    @BindingAdapter("id", "pw", "token")
    @JvmStatic
    fun login(button: Button, id : String?, pw : String?, token : String?) {
        button.setOnClickListener {

            val retrofitBuilder = RetrofitBuilder
            retrofitBuilder.networkService.login(id.toString(), pw.toString(), token.toString()).enqueue(
                object : Callback<ResponseLoginData> {
                    override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {

                    }
                    override fun onResponse(
                        call: Call<ResponseLoginData>,
                        response: Response<ResponseLoginData>
                    ) {
                        SingleTon.prefs.userToken = response.body()?.data?.token

                        val intent = Intent(button.context, CourseMosActivity::class.java)
                        button.context.startActivity(intent)

                    }
                }
            )
        }
    }

    @BindingAdapter("setSchoolLogo")
    @JvmStatic
    fun setLogo(imageView: ImageView, url : String?) {
        Glide.with(imageView.context).load(url).error(R.drawable.ic_launcher_foreground).into(imageView)
    }

}