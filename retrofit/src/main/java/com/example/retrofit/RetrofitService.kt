package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET

class StudentFromServer (
    val id: Int, val name: String, val age : Int, val intro: String
)

interface RetrofitService {

    @GET("json/students")
    fun getStudentList():Call<ArrayList<StudentFromServer>>
}