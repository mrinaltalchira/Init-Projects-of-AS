package com.example.bookshelf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Sign_up_Activity : AppCompatActivity() {

    lateinit var txtLogin : TextView
    private lateinit var auth: FirebaseAuth
    lateinit var edtNewMail: EditText
    lateinit var edtNewPass: EditText
    lateinit var btnSignup: Button
    lateinit var username : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        edtNewMail = findViewById(R.id.edtNewMail)
        edtNewPass = findViewById(R.id.edtNewPass)
        btnSignup = findViewById(R.id.btnSignUp2)
        txtLogin = findViewById(R.id.txtLoginlink)
        username= findViewById(R.id.edtNewName)

        txtLogin.setOnClickListener {
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener {
            val mail = edtNewMail.text.toString()
            val pass = edtNewPass.text.toString()

            if (mail.isNullOrEmpty() || pass.isNullOrEmpty()) {
                Toast.makeText(
                    this@Sign_up_Activity,
                    "First please enter username & password",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                auth.createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            var intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(this, "please enter valid details", Toast.LENGTH_LONG)
                                .show()
                        }

                    }
            }

        }


    }

    override fun onPause() {
        finish()
        super.onPause()
    }
}