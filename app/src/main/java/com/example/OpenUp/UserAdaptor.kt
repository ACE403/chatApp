package com.example.OpenUp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.OpenUp.R
import com.example.OpenUp.databinding.UserLayoutBinding
import com.google.android.gms.wearable.DataItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserAdaptor(var context: Context,var userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdaptor.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser =userList[position]
        holder.textname.text=currentUser.name

        holder.itemView.setOnClickListener {
            val intent =Intent(context,ChatActivity::class.java)
            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid", currentUser.uid)
            context.startActivity(intent)
        }
    }
    override fun getItemCount():Int{
return userList.size
    }

    class UserViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
val textname = itemView.findViewById<TextView>(R.id.textView)
        val binding:UserLayoutBinding = UserLayoutBinding.bind(itemView)
//        val image = itemView.findViewById<ImageView>(R.id.imageView)
    }

}




