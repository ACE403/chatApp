package com.example.OpenUp


import android.R
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.OpenUp.databinding.ActivityChatBinding
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageAdaptor:MessageAdapter
    private lateinit var messageList:ArrayList<Message>
    private lateinit var databaseref:DatabaseReference

    private var receiverRoom: String? =null
    private var senderRoom: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val name =         intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")

       // val senderUid = FirebaseAuth.getInstance().uid
        val senderUid = Firebase.auth.uid
        databaseref=FirebaseDatabase.getInstance().reference

//        senderRoom =  receiverUid + senderUid
//        receiverRoom = senderUid + receiverUid
        senderRoom= senderUid + receiverUid
        receiverRoom=  receiverUid + senderUid

        supportActionBar?.title=name

        messageList=ArrayList()
        messageAdaptor= MessageAdapter(this,messageList)
        chatRecyclerView =binding.chatRecyclerView

        chatRecyclerView.layoutManager=LinearLayoutManager(this)
        chatRecyclerView.adapter =messageAdaptor
 // adding data to recyclerview
        databaseref
            .child("chats")
            .child(senderRoom!!)
            .child("messages")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for(postSnapshot in snapshot.children){
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdaptor.notifyDataSetChanged()
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })



        binding.sendMessage.setOnClickListener {
         val message = binding.messagebox.text.toString()
            val messageObject =Message(message,senderUid)

            databaseref
                .child("chats")
                .child(senderRoom!!)
                .child("messages")
                .push()
                .setValue(messageObject).addOnSuccessListener {
                    databaseref
                        .child("chats")
                        .child(receiverRoom!!)
                        .child("messages")
                        .push()
                        .setValue(messageObject)
                }
            binding.messagebox.setText("")

        }

    }

}