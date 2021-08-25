package com.gr.assignment.network

import android.text.Html
import com.gr.assignment.data.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import javax.security.auth.Subject

interface NetworkService {

    @GET("/coding_test.php")
    fun getLectureInfo() : Call<ResponseData>

    @GET("/test/school_list")
    fun getSchoolList() : Call<ResponseSchoolListData>

    @GET("/test/get_school_info")
    fun getSchoolDetail(
        @Query("id") id : Int
    ) : Call<ResponseSchoolDetailData>

    @GET("/test/login")
    fun login(
        @Query("id") id : String,
        @Query("pw") pw : String,
        @Query("token") token : String
    ) : Call<ResponseLoginData>

    @GET("/test/course_list")
    fun getCourseList(
        @Header("Authorization") authorHeader : String,
        @Query("token") token: String
    ) : Call<ResponseCourseListData>

    @GET("/test/course_contents")
    fun getCourseContent(
        @Query("course_id") courseId : Int,
        @Query("token") token : String
    ) : Call<ResponseCourseContentData>

    @GET("test/board")
    fun getBoard(
        @Query("contents_id") contentsId : Int,
        @Query("token") token: String
    ) : Call<ResponseBoardData>

    @POST("/test/board")
    fun boardWrite(
        @Body postData: PostData
    ) : Call<DefaultResponseBody>

    @HTTP(method = "DELETE", path = "/test/board", hasBody = true)
    fun deleteBoard(
        @Body deleteData : DeleteData
    ) : Call<DefaultResponseBody>

    @GET("/test/web")
    fun getWebView(
        @Header("token") token: String,
        @Header("contentsid") contentsId: Int
    ) : Call<Html>
}