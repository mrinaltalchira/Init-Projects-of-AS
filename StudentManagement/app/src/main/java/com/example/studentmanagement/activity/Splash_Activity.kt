package com.example.studentmanagement.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.studentmanagement.R

class Splash_Activity : AppCompatActivity() {

    lateinit var imag : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

imag = findViewById(R.id.logo)

        imag.alpha = 0f
        imag.animate().setDuration(1500).alpha(1f).withEndAction{

            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }


    }
}