package com.example.studentmanagement.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.studentmanagement.R
import com.example.studentmanagement.databinding.ActivityForgetpassBinding
import com.google.firebase.auth.FirebaseAuth

class Forgetpass : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    lateinit var binding: ActivityForgetpassBinding
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityForgetpassBinding.inflate(layoutInflater)

        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        binding.submitButton.setOnClickListener {
            val email = binding.mail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(applicationContext, "Enter your email!", Toast.LENGTH_SHORT).show()
            } else {
                mAuth!!.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Check email to reset your password!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Fail to send reset password email!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

    }



}