package com.example.OpenUp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.OpenUp.databinding.ActivityLoginInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.*
import com.google.firebase.ktx.Firebase


class Login_In : AppCompatActivity() {
    private lateinit var binding: ActivityLoginInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
          binding.SignUptextView.setOnClickListener{
          val intent = Intent(this,Sign_Up::class.java)
          startActivity(intent)
       }
        binding.LoginButton.setOnClickListener{
            val email= binding.emailText.text.toString()
        val password =binding.passwordText.text.toString()

         login(email,password)
        }



    }

    private fun login(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this@Login_In, MainActivity::class.java)
                        finish()
                        startActivity(intent)


                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this@Login_In, " User does not exist",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
            else {
                Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
