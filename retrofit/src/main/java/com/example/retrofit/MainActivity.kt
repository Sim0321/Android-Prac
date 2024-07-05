package com.example.retrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.getStudentList().enqueue(object : Callback<ArrayList<StudentFromServer>>{
            override fun onResponse(
                call: Call<ArrayList<StudentFromServer>>,
                response: Response<ArrayList<StudentFromServer>>
            ) {
                if(response.isSuccessful){
                    val studentList = response.body()
                    Log.d("testt", "잘됨?")

                    findViewById<RecyclerView>(R.id.studentsRecyclerView).apply{
                        this.adapter = StudentListRecyclerViewAdapter(
                            studentList!!,
                            LayoutInflater.from(this@MainActivity)
                        )
                        this.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }

            override fun onFailure(p0: Call<ArrayList<StudentFromServer>>, p1: Throwable) {
                TODO("Not yet implemented")
            }
            //            override fun onResponse(
//                call: Call<ArrayList<StudentFromServer>>,
//                response: Response<ArrayList<StudentFromServer>>
//            ) {
//                if(response.isSuccessful){
//                    val studentList = response.body()
////                    studentList!!.forEach{
////                        Log.d("testt", ""+it)
////                    }
//                    findViewById<RecyclerView>(R.id.studentsRecyclerView).apply{
//                        this.adapter = StudentListRecyclerViewAdapter(
//                            studentList!!,
//                            LayoutInflater.from(this@MainActivity)
//                        )
//                        this.layoutManager = LinearLayoutManager(this@MainActivity)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ArrayList<StudentFromServer>>, t: Throwable) {
//
//            }
        })

    }
}

class StudentListRecyclerViewAdapter(
    val studentList : ArrayList<StudentFromServer>,
    var inflater : LayoutInflater
) : RecyclerView.Adapter<StudentListRecyclerViewAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val studentName : TextView
        val studentAge : TextView
        val studentIntro : TextView

        init {
            studentName = itemView.findViewById(R.id.student_name)
            studentAge = itemView.findViewById(R.id.student_age)
            studentIntro = itemView.findViewById(R.id.student_intro)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.studentName.text = studentList.get(position).name
        holder.studentAge.text = studentList.get(position).age.toString()
        holder.studentIntro.text = studentList.get(position).intro
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

}