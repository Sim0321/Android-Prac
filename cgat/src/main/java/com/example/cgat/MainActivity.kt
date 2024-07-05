package com.example.cgat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chatList = mutableListOf<Chat>()


        chatList.add(Chat("안녕하세요", true))
        chatList.add(Chat("김가람씨", true))
        chatList.add(Chat("밀리오 좀 잘해봐요", true))
        chatList.add(Chat("넵.. 죄송합니디ㅏ", false))
        chatList.add(Chat("분발할게요", false))
        chatList.add(Chat("그래서 노틸은?", true))
        chatList.add(Chat("아.. 그게..", false))
        chatList.add(Chat("또 변명이야???", true))
        chatList.add(Chat("야이 개스꺄", false))
        chatList.add(Chat("뭐? 개스꺄?", true))
        chatList.add(Chat("그래 이 개스꺄 내가 만만해?!", false))
        chatList.add(Chat("응!", true))

        val adapter = ChatRecyclerAdapter(
            chatList = chatList,
            inflater = LayoutInflater.from(this@MainActivity)
        )

        with(findViewById<RecyclerView>(R.id.chatRecyclerView)){
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            adapter.chatList.add(2, Chat("테스트", false))
//            adapter.notifyItemInserted(2) // 추가됐을 때
//            adapter.notifyitemChanged() // 변화가 있을 때
//            adapter.notifyItemRemoved() // 제거될 떄
//            adapter.notifyItemChanged() // 순서 변경
//        }
    }
}

class ChatRecyclerAdapter(
    val chatList : MutableList<Chat>,
    val inflater : LayoutInflater
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class RightViewHolder( itemView : View): RecyclerView.ViewHolder(itemView){
        val rightTextView : TextView
        init{
            rightTextView = itemView.findViewById(R.id.chatTextRight)
        }
    }

    class LeftViewHolder( itemView : View): RecyclerView.ViewHolder(itemView){
        val leftTextView : TextView
        init{
            leftTextView = itemView.findViewById(R.id.chatTextLeft)
        }
    }

    override fun getItemViewType(position: Int): Int {
        when(chatList.get(position).is_right){
            true -> return 1
            false -> return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            1 -> return RightViewHolder(inflater.inflate(R.layout.chatitem_right, parent, false))
            else -> return LeftViewHolder(inflater.inflate(R.layout.chatitem_left, parent, false))


        }



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList.get(position)
        when(chat.is_right){
            true -> (holder as RightViewHolder).rightTextView.text = chat.message
            false -> (holder as LeftViewHolder).leftTextView.text = chat.message
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}


class Chat(val message : String, val is_right : Boolean)

}