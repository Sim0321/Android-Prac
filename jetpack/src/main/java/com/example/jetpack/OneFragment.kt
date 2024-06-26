package com.example.jetpack

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.databinding.FragmentOneBinding
import com.example.jetpack.databinding.ItemRecyclerviewBinding

// 항목 뷰를 가지는 역할
class MyViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

// 항목 구성자. 어뎁터
class MyAdapter(val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    // 항목 개수를 판단하기 위해 자동 호출
    override fun getItemCount() : Int {
        return datas.size
    }

    // 항목 뷰를 가지는 뷰 홀더를 준비하기 위해 자동 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    // 각 항목을 구성하기 위해 호출
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val binding=(holder as MyViewHolder).binding
        // 뷰에 데이터 출력
        binding.itemData.text = datas[position]
    }
}

// 리사이클러 뷰 꾸미기
class MyDecoration(val context: Context) : RecyclerView.ItemDecoration(){
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state:RecyclerView.State){
        super.onDrawOver(c, parent, state)
        // 뷰 크기 계산
        val width = parent.width
        val height = parent.height
        // img 크기 계산
        val dr: Drawable? = ResourcesCompat.getDrawable(context.getResources(), R.drawable.kbo, null)
        val drWidth = dr?.intrinsicWidth
        val drHeight = dr?.intrinsicHeight

        // 이미지를 출력할 위치 계산
        val left = width/2 - drWidth?.div(2) as Int
        val top = height/2 - drHeight?.div(2) as Int

        c.drawBitmap(
            BitmapFactory.decodeResource(context.getResources(), R.drawable.kbo),
            left.toFloat(),
            top.toFloat(),
            null
        )
    }

    // 각 항목을 꾸미기 위해 호출
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent:RecyclerView,
        state: RecyclerView.State){
        super.getItemOffsets(outRect, view, parent, state)
        var index = parent.getChildAdapterPosition(view) + 1

        if(index % 3 ==0)
            outRect.set(10,10,10,60)
        else
            outRect.set(10,10,10,10)

        view.setBackgroundColor(Color.parseColor("#28A0FF"))
        ViewCompat.setElevation(view, 20.0f)
    }

}

class OneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        val datas = mutableListOf<String>()
        for(i in 1..10){
            datas.add("Item $i")
        }

        val adapter = MyAdapter(datas)
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))
        return binding.root

    }

}



