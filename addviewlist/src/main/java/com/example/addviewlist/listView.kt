package com.example.addviewlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class listView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        // 데이터 준비
        var bookList = mutableListOf<PhoneBook>()
        for(i in 0 .. 100){
            bookList.add(PhoneBook(""+i+"번째 책", ""+i+ "번째"))
        }

        // 어뎁터 준비
        val adapter = ListViewAdapter(
            bookList,
            LayoutInflater.from(this),
            this
        )

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

    }
}

class ListViewAdapter(
    val bookList : MutableList<PhoneBook>,
    val layoutInflater: LayoutInflater,
    val context: Context
) : BaseAdapter(){
    override fun getCount(): Int {
        // 전체 데이터의 크기
        return bookList.size

    }

    override fun getItem(position: Int): Any {
        // 전체 데이터 중에서 해당번째(position)의 데이터를 리턴
        return bookList.get(position)
    }

    override fun getItemId(position: Int): Long {
         return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // 해당 번째 뷰를 리턴
        val view = layoutInflater.inflate(R.layout.phonebook_item, null)
        val image =  view.findViewById<ImageView>(R.id.image)
        val name = view.findViewById<TextView>(R.id.name)
        val number = view.findViewById<TextView>(R.id.number)

        val book = bookList.get(position)
        image.setImageDrawable(
            context.resources.getDrawable(R.drawable.baduck, context.theme)
        )
        name.text = book.name
        number.text = book.number

        return view
    }
}