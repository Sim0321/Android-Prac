package com.example.livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
     var value = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.varData = this

        // 데이터 바인딩


//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        뷰 바인딩/데이터 바인딩 하지 않았을 때
//        findViewById<TextView>(R.id.result).text = value.toString()
//
//        findViewById<Button>(R.id.btn_plus).setOnClickListener {
//            value += 1
//            findViewById<TextView>(R.id.result).text = value.toString()
//        }
//
//        findViewById<Button>(R.id.btn_minus).setOnClickListener {
//            value -= 1
//            findViewById<TextView>(R.id.result).text = value.toString()
//        }
//
//        findViewById<Button>(R.id.btn_double).setOnClickListener {
//            value *= 2
//            findViewById<TextView>(R.id.result).text = value.toString()
//        }

//        뷰 바인딩 사용
//        binding.result.text = value.toString()
//
//        binding.btnPlus.setOnClickListener {
//            value += 1
//            binding.result.text = value.toString()
//        }
//
//        binding.btnMinus.setOnClickListener {
//            value -= 1
//            binding.result.text = value.toString()
//        }
//
//        binding.btnDouble.setOnClickListener {
//            value *= 2
//            binding.result.text = value.toString()
//        }

    }
    fun onPlusClick(){
        value += 1
        binding.invalidateAll()
    }

    fun onMinusClick(){
        value -= 1
        binding.invalidateAll()
    }

    fun onDoubleClick(){
        value *=2
        binding.invalidateAll()
    }
}