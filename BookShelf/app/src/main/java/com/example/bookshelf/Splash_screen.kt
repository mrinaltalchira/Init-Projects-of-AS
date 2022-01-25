package com.example.bookshelf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Splash_screen : AppCompatActivity() {

    lateinit var imgSplash: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        imgSplash = findViewById(R.id.imgSplash)

        imgSplash.alpha = 0f
        imgSplash.animate().setDuration(1500).alpha(1f).withEndAction {
            var intent = Intent(this@Splash_screen, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()

        }
    }

}