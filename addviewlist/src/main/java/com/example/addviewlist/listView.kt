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
import android.widget.Toast
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

        // 어뎁터 장착
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter


        // 리스너 장착
        listView.setOnItemClickListener { parent, view, position, id ->
            val book : PhoneBook = adapter.bookList.get(position)
            val name = book.name
            val number = book.number

            Toast.makeText(
                this,
                name + " " + number,
                Toast.LENGTH_LONG
            ).show()
        }

        // 데이터 갱신 방법
        findViewById<TextView>(R.id.addBook).setOnClickListener {
            adapter.bookList.add(
                PhoneBook("안녕 내 이름은 책", "안녕 내 번호는 번호")
            )
            adapter.notifyDataSetChanged()
        }


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


        val view: View
        val holder : ViewHolder

        // 재활용 불가능
        if(convertView == null){
            view = layoutInflater.inflate(R.layout.phonebook_item, null)
            holder = ViewHolder()

            holder.bookImage = view.findViewById(R.id.image)
            holder.name = view.findViewById(R.id.name)
            holder.number = view.findViewById(R.id.number)

            view.tag = holder
        } else {
            // 재활용 가능
            holder = convertView.tag as ViewHolder
            view = convertView

        }

        val book = bookList.get(position)

        holder.bookImage?.setImageDrawable(
            context.resources.getDrawable(R.drawable.baduck, context.theme)
        )
        holder.name?.text = book.name
        holder.number?.text = book.number

        // 해당 번째 뷰를 리턴
//        val view = layoutInflater.inflate(R.layout.phonebook_item, null)
//        val image =  view.findViewById<ImageView>(R.id.image)
//        val name = view.findViewById<TextView>(R.id.name)
//        val number = view.findViewById<TextView>(R.id.number)
//
//        val book = bookList.get(position)
//        image.setImageDrawable(
//            context.resources.getDrawable(R.drawable.baduck, context.theme)
//        )
//        name.text = book.name
//        number.text = book.number

        return view
    }
}

class ViewHolder {
    var bookImage : ImageView? = null
    var name : TextView? = null
    var number : TextView? = null
}