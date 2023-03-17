package com.example.OpenUp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.OpenUp.R
import com.example.OpenUp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Sign_Up : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.LogIntextView.setOnClickListener{
            val intent = Intent(this,Login_In::class.java)
            startActivity(intent)
        }
        binding.SignUpbutton.setOnClickListener{
            val name=binding.nameText.text.toString()
            val email= binding.emailText.text.toString()
            val password =binding.passwordText.text.toString()
            val confirmpass = binding.confirmPasswordText.text.toString()
            SignUp(name,email,password,confirmpass)
        }



    }

    private fun SignUp(name:String,email: String, password: String,confirmpass:String) {
        if (email.isNotEmpty() && password.isNotEmpty() && confirmpass.isNotEmpty()) {
            if (password == confirmpass) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,auth.currentUser!!.uid)
                   val intent= Intent(this@Sign_Up,Login_In::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(this@Sign_Up,  "Authentication failed",
                        Toast.LENGTH_SHORT).show()

                }
            }
            } else {
                Toast.makeText(this, "password does not match", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show()
        }


    }

    private fun addUserToDatabase(name: String, uid: String) {
databaseref=FirebaseDatabase.getInstance().getReference()

        databaseref.child("user")
            .child(uid)
            .setValue(User(name,uid))
    }
}

