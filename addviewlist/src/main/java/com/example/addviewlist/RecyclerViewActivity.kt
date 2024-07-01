package com.example.addviewlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val bookList = mutableListOf<PhoneBook>()
        for(i in 0 .. 100){
            bookList.add(PhoneBook( "책 이름은" + "" + i, "" + i + "번째 책"))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // 어뎁터 장착
        recyclerView.adapter = RecyclerViewAdapter(bookList, LayoutInflater.from(this))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

class RecyclerViewAdapter(
    // outer class
    var bookList: MutableList<PhoneBook>,
    var inflater: LayoutInflater
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        // inner class
        // item view의 상세 뷰 컴포넌트를 홀드
        val bookImage: ImageView
        val name : TextView
        val number : TextView

        init{
            bookImage = itemView.findViewById(R.id.image)
            name = itemView.findViewById(R.id.name)
            number = itemView.findViewById(R.id.number)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // item view return
        val view = inflater.inflate(R.layout.phonebook_item, parent, false)
        return RecyclerViewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 데이터를 item view의 view 컴포넌트와 바인딩
        holder.name.text = bookList.get(position).name
        holder.number.text = bookList.get(position).number
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}
