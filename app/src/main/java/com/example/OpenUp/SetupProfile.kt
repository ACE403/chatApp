package com.example.OpenUp

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.OpenUp.R
import com.example.OpenUp.databinding.ActivityProfileSetupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.HashMap

class SetupProfile:AppCompatActivity() {
    var binding: ActivityProfileSetupBinding? = null
    var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var storage: FirebaseStorage? = null
    var selectedImage: Uri? = null
    var dialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSetupBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
//
//        dialog!!.setMessage("Updating Profile... ")
//        dialog!!.setCancelable(false)
//
//        database = FirebaseDatabase.getInstance()
//        storage = FirebaseStorage.getInstance()
//        auth = FirebaseAuth.getInstance()
//        supportActionBar?.hide()
//
//        binding!!.profileImageView.setOnClickListener {
//            val intent = Intent()
//            intent.action = Intent.ACTION_GET_CONTENT
//            intent.type = "image/*"
//            startActivityForResult(intent, 45)
//        }
//        binding!!.SetupButton.setOnClickListener {
//            val name: String = binding!!.nameText.text.toString()
//            if (name.isEmpty())
//                binding!!.nameText.setError("Cannot be empty")
//        }


//        dialog!!.show()
//        if(selectedImage != null){
//            val reference = storage!!.reference.child("Profile")
//                .child(auth!!.uid!!)
//            reference.putFile(selectedImage!!).addOnCompleteListener{ task->
//            if(task.isSuccessful){
//               reference.downloadUrl.addOnCompleteListener{ uri ->
//                  val imageUrl=uri.toString()
//                   val uid =auth!!.uid
//                   val name:String =binding!!.nameText.text.toString()
//                   val user=User(name,uid,imageUrl)
//                   database!!.reference
//                       .child("users")
//                       .child(uid!!)
//                       .setValue(user)
//                       .addOnCompleteListener{
//                           dialog!!.dismiss()
//                           val intent=Intent(this,MainActivity::class.java)
//                           startActivity(intent)
//                           finish()
//                       }
//
//    }
//}
//                else{
//                val uid =auth!!.uid
//                val name:String =binding!!.nameText.text.toString()
//                val user=User(uid,name,"no image")
//                database!!.reference
//                    .child("users")
//                    .child(uid!!)
//                    .setValue(user)
//                    .addOnCanceledListener {
//                        dialog!!.dismiss()
//                        val intent=Intent(this,MainActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    }
//            }
//            }
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(data !=null){
//            if(data.data !=null){
//                val uri=data.data
//                val storage=FirebaseStorage.getInstance()
//                val time= Date().time
//                val uid =auth!!.uid
//                val reference=storage.reference
//
//                    .child("Profile")
//                    .child(time.toString() + "")
//                reference.putFile(uri!!).addOnCompleteListener { task->
//                    if(task.isSuccessful){
//                        reference.downloadUrl.addOnCompleteListener{
//                            val filepath =uri.toString()
//                            val obj = HashMap<String,Any>()
//                            obj["image"]=filepath
//                            database!!.reference
//                                .child("users")
//                                .child(uid!!)
//                                .updateChildren(obj).addOnSuccessListener {  }
//
//                        }
//                    }
//                }
//binding!!.profileImageView.setImageURI(data.data)
//                selectedImage=data.data
//
//            }
//        }
    }
 }