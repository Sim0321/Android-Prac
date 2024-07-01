package com.example.addviewlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var phoneBookList = mutableListOf<PhoneBook>()

        for(i in 0..40){
            phoneBookList.add(
                PhoneBook(""+i+"번째 사람", "010-0000-000"+i)
            )
        }

        Log.d("testt", "여기 찍힘?")

        val container = findViewById<LinearLayoutCompat>(R.id.container)
        val inflater = LayoutInflater.from(this)

        phoneBookList.forEach {phoneBook ->

            val phonebookItem = inflater.inflate(R.layout.phonebook_item, null)
            val image = phonebookItem.findViewById<ImageView>(R.id.image)
            val name = phonebookItem.findViewById<TextView>(R.id.name)
            val number = phonebookItem.findViewById<TextView>(R.id.number)

            image.setImageDrawable(resources.getDrawable(R.drawable.baduck, this.theme))
            name.text = phoneBook.name
            number.text = phoneBook.number

            container.addView(phonebookItem)

            phonebookItem.setOnClickListener{
                startActivity(
                    Intent(this, DetailActivity::class.java).apply{
                        this.putExtra("name", phoneBook.name)
                        this.putExtra("number", phoneBook.number)
                    }
                )
            }
        }

    }
}

class PhoneBook(val name : String, val number : String){

}