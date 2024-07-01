package com.example.addviewlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val bookList = mutableListOf<PhoneBook>()
        for(i in 0 .. 100){
            bookList.add(PhoneBook( "책 이름은" + "" + i, "" + i + "번째 책"))
        }

    }
}

class RecyclerViewAdapter(
    // outer class
    var bookList: MutableList<PhoneBook>,
    var inflater: LayoutInflater
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // inner class
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }
}
