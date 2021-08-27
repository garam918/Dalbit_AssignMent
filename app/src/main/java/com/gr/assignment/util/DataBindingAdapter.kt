package com.gr.assignment.util

import android.content.Intent
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
import com.gr.assignment.R
import com.gr.assignment.RequestFragmentDialog
import com.gr.assignment.SingleTon
import com.gr.assignment.data.*
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

    // spinner를 활용해 대학들의 목록을 표시했습니다.
    @BindingAdapter("setSpinner")
    @JvmStatic
    fun setSpinner(spinner: Spinner, items: MutableLiveData<ArrayList<String>>) {
        val adapter = ArrayAdapter(spinner.context,android.R.layout.simple_spinner_item,items.value!!)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
    }

    @BindingAdapter("id", "pw")
    @JvmStatic
    fun login(button: Button, id : String?, pw : String?) {
        button.setOnClickListener {
                RetrofitBuilder.networkService.login(id.toString(), pw.toString(), SingleTon.prefs.schoolToken.toString())
                    .enqueue(
                        object : Callback<ResponseLoginData> {
                            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {

                            }

                            override fun onResponse(
                                call: Call<ResponseLoginData>,
                                response: Response<ResponseLoginData>
                            ) {
                                val res = response.body()!!
                                when (res.message) {

                                    // message가 success인 경우에만 다음화면으로 넘어가게 구현했습니다.
                                    "success" -> {

                                        SingleTon.prefs.userToken = response.body()?.data?.token

                                        val intent = Intent(button.context, CourseMosActivity::class.java)
                                        button.context.startActivity(intent)

                                    }
                                    "계정을 확인해주세요." -> Toast.makeText(button.context,res.message,Toast.LENGTH_SHORT).show()
                                    "토큰이 만료되었습니다" -> {
                                        Toast.makeText(button.context,"토큰이 만료되었씁니다.\n학교를 다시 선택해주세요", Toast.LENGTH_SHORT).show()
                                    }
                                    else -> Toast.makeText(button.context,"로그인에 실패했습니다.",Toast.LENGTH_SHORT).show()
                                }
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

    @BindingAdapter("contentsId" ,"contentsName", "subject", "content", "subjectEditText","contentEditText")
    @JvmStatic
    fun confirm(button: Button, contentsId: Int, contentsName: String, subject : String?, content : String?, subjectEdit : EditText, contentEdit : EditText) {

        button.setOnClickListener {

            val data = PostData(contentsId,
                SingleTon.prefs.userToken.toString(),subject.toString(),content.toString())

            RetrofitBuilder.networkService.boardWrite(data).enqueue(object : Callback<DefaultResponseBody> {
                override fun onFailure(call: Call<DefaultResponseBody>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<DefaultResponseBody>,
                    response: Response<DefaultResponseBody>
                ) {
                    val res = response.body()!!
                    val bundle = bundleOf("contentsId" to contentsId, "contentsName" to contentsName)
                    if(res.message == "success") {
                        subjectEdit.text.clear()
                        contentEdit.text.clear()
                        it.findNavController().navigate(R.id.action_navigation_board_write_to_navigation_content, bundle)
                    }
                    else {
                        RequestFragmentDialog().show((it.context as CourseMosActivity).supportFragmentManager,"11")
                    }

                }
            })
        }
    }

    @BindingAdapter("contentsId", "contentsName", "subjectEditText","contentEditText")
    @JvmStatic
    fun setCancel(button: Button, contentsId: Int, contentsName : String, subjectEdit : EditText, contentEdit : EditText) {
        button.setOnClickListener {
            subjectEdit.text.clear()
            contentEdit.text.clear()
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

    @BindingAdapter("contentsId", "contentsName")
    @JvmStatic
    fun setPrevious(imageButton: ImageButton, contentsId: Int, contentsName: String) {
        imageButton.setOnClickListener {
            val bundle = bundleOf("contentsId" to contentsId, "contentsName" to contentsName)
            it.findNavController().navigate(R.id.action_navigation_web_view_to_navigation_content, bundle)
        }
    }
}