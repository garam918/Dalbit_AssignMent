package com.gr.assignment.util

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gr.assignment.CourseMosActivity
import com.gr.assignment.MainActivity
import com.gr.assignment.R
import com.gr.assignment.SingleTon
import com.gr.assignment.data.*
import com.gr.assignment.network.RetrofitBuilder
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType
import okhttp3.RequestBody
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
                        SingleTon.prefs.schoolToken = token

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

    @BindingAdapter("setPreviousOnClick")
    @JvmStatic
    fun setOnclick(button: ImageButton, name : String) {
        button.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_view_pager_to_navigation_home)
        }
    }

    @BindingAdapter("courseItem")
    @JvmStatic
    fun setCourseItem(recyclerView: RecyclerView, items: ObservableArrayList<CourseDetailData>) {
        if(recyclerView.adapter == null) {
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = ContentsRecyclerAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as ContentsRecyclerAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()
    }

    @BindingAdapter("boardItem")
    @JvmStatic
    fun setBoardItem(recyclerView: RecyclerView, items: ObservableArrayList<BoardDetailData>) {
        if(recyclerView.adapter == null) {
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = BoardRecyclerAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as BoardRecyclerAdapter).items = items
        recyclerView.adapter?.notifyDataSetChanged()
    }

    @BindingAdapter("boardWrite")
    @JvmStatic
    fun boardWrite(button: Button, contentsId : Int) {
        val bundle = bundleOf("contentsId" to contentsId)

        button.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_content_to_navigation_board_write,bundle)
        }
    }

    @BindingAdapter("contentsId" ,"contentsName", "token", "subject", "content")
    @JvmStatic
    fun confirm(button: Button, contentsId: Int, contentsName: String, token: String, subject : String?, content : String?) {

        button.setOnClickListener {

            val data = PostData(contentsId,token,subject.toString(),content.toString())

            RetrofitBuilder.networkService.boardWrite(data).enqueue(object : Callback<DefaultResponseBody> {
                override fun onFailure(call: Call<DefaultResponseBody>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<DefaultResponseBody>,
                    response: Response<DefaultResponseBody>
                ) {
                    val res = response.body()!!
                    val bundle = bundleOf("contentsId" to contentsId, "contentsName" to contentsName)
                    Log.e("res",res.toString())
                    if(res.message == "success") {
                        it.findNavController().navigate(R.id.action_navigation_board_write_to_navigation_content, bundle)
                    }


                }
            })
        }
    }

    @BindingAdapter("contentsId", "contentsName")
    @JvmStatic
    fun setCancel(button: Button, contentsId: Int, contentsName : String) {
        button.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_board_write_to_navigation_content, bundleOf("contentsId" to contentsId , "contentsName" to contentsName))
        }
    }

    @BindingAdapter("courseName","courseId")
    @JvmStatic
    fun setPrevious(imageButton: ImageButton, courseName : String, courseId: Int) {
        val bundle = bundleOf("courseName" to courseName, "courseId" to courseId)
        imageButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_content_to_navigation_view_pager,bundle)
        }
    }

//    @BindingAdapter("setReLogin")
//    @JvmStatic
//    fun setAutoLogin(button: Button) {
//        button.setOnClickListener {
//            val intent = Intent(it.context,MainActivity::class.java)
//            button.context.startActivity(intent)
//        }
//    }

}