package com.example.livedata

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var value = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.result).text = value.toString()

        findViewById<Button>(R.id.btn_plus).setOnClickListener {
            value += 1
            findViewById<TextView>(R.id.result).text = value.toString()
        }

        findViewById<Button>(R.id.btn_minus).setOnClickListener {
            value -= 1
            findViewById<TextView>(R.id.result).text = value.toString()
        }

        findViewById<Button>(R.id.btn_double).setOnClickListener {
            value *= 2
            findViewById<TextView>(R.id.result).text = value.toString()
        }

    }
}