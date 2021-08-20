package com.gr.assignment.network

import com.gr.assignment.data.*
import retrofit2.Call
import retrofit2.http.*

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
        @Body contents_id: Int,
        @Body token: String,
        @Body subject : String,
        @Body content : String
    ) : Call<ResponseBoardData>

    @DELETE("/test/board")
    fun deleteBoard(
        @Body board_id : Int,
        @Body token : String
    ) : Call<ResponseBoardData>

    @GET("/test/web")
    fun getWebView(
        @Header("token") token: String,
        @Header("contentsid") contentsId: Int
    ) : Call<ResponseBoardData>
}