package com.example.studentmanagement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studentmanagement.R
import com.example.studentmanagement.databinding.ActivityMainBinding
import com.example.studentmanagement.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        binding.btnSignup.setOnClickListener {
            val mail = binding.edtSignupMail.text.toString()
            val pass = binding.edtSignupPass.text.toString()
            val name = binding.edtSignupName.text.toString()
            val contact = binding.edtSignupContact.text.toString()

            if (mail.isBlank() || pass.isBlank() || name.isBlank() || contact.isBlank()) {
                Toast.makeText(
                    this,
                    "First please enter all details",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                auth.createUserWithEmailAndPassword(mail,pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)


                        } else {
                            Toast.makeText(this, "please enter valid details", Toast.LENGTH_LONG)
                                .show()
                        }

                    }
            }

        }




        binding.forgetpassss.setOnClickListener {
            val intent = Intent(this,Forgetpass::class.java)
            startActivity(intent)

        }

        binding.txtLogin.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }




    }

    override fun onPause() {
        finish()
        super.onPause()
    }
}