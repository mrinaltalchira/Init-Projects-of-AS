package com.example.bookshelf

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {


    lateinit var edtUserNameLog: EditText
    lateinit var edtPasswordLog: EditText
    lateinit var btnLogin : Button
    lateinit var txtSignUp: TextView
    lateinit var auth: FirebaseAuth
    lateinit var txtForgetPass: TextView
    lateinit var sharePref: SharedPreferences
    lateinit var tit: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharePref = getSharedPreferences(getString(R.string.sharedtext), MODE_PRIVATE)
        setContentView(R.layout.activity_login)


        edtUserNameLog = findViewById(R.id.edtUserNameLogin)
        edtPasswordLog = findViewById(R.id.edtPasswordLogin)
        btnLogin = findViewById(R.id.btnLogin2)
        txtSignUp = findViewById(R.id.txtSignUp)
        auth = Firebase.auth
        txtForgetPass = findViewById(R.id.txtForgetPass)
        tit = edtUserNameLog.text.toString()
        val login = sharePref.getBoolean("islogin", false)
        if (login) {

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        txtForgetPass.setOnClickListener {
            val intent = Intent(this, ForgetpassActivity::class.java)
            startActivity(intent)
        }

        txtSignUp.setOnClickListener {
            val intent = Intent(this, Sign_up_Activity::class.java)
            startActivity(intent)
        }



        btnLogin.setOnClickListener {

            val password = edtPasswordLog.text.toString()
            val userName = edtUserNameLog.text.toString()
            tit = edtUserNameLog.text.toString()

            if (password.isEmpty() || userName.isEmpty()) {
                Toast.makeText(this, "please enter details", Toast.LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(this) {

                        task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        savedata(tit)
                        startActivity(intent)
                        savedata(tit)
                        finish()
                    } else {
                        Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                    }


                }
            }


        }



    }

    fun savedata(titleName: String) {
        sharePref.edit().putBoolean("islogin", true).apply()
        sharePref.edit().putString("titlename", titleName).apply()

    }

}