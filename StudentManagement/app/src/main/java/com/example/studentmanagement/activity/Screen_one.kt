package com.example.studentmanagement.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity.START
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.studentmanagement.R
import com.example.studentmanagement.databinding.ActivityScreenOneBinding
import com.example.studentmanagement.fragment.Aboutus
import com.example.studentmanagement.fragment.Help
import com.example.studentmanagement.fragment.Homescreen
import com.google.firebase.auth.FirebaseAuth


class Screen_one : AppCompatActivity() {

    lateinit var binding: ActivityScreenOneBinding
    var previousMenuItem: MenuItem? = null
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        defaultFrag()
        binding.navigationView.setCheckedItem(R.id.menu_dash)

        // Configure action bar
        setSupportActionBar(binding.myToolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Hello there"

         setsupport() // calling Actionbar


        val actionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                binding.deawerLayout,
                R.string.open,
                R.string.close
            ) // preparing action bar drawer toggle



        binding.deawerLayout.addDrawerListener(actionBarDrawerToggle) // putting drawer toggle into drawerlayout
        actionBarDrawerToggle.syncState() // syncing state of toggl
        // by this sync we allow the toggle/hamburgericon  that if state if oprn than create back sign and if is in
        // then create that hamburger icon


        binding.navigationView.setNavigationItemSelectedListener {              //putting click listner on Navigationbar

            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }
            it.isChecked = true
            it.isCheckable = true
            previousMenuItem = it

            when (it.itemId) {
                R.id.menu_dash -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, Homescreen())
                        .addToBackStack("Dashboard").commit()
                    binding.deawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.menu_help -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, Help())
                        .addToBackStack("Help").commit()
                    binding.deawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.menu_about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.framelayout, Aboutus())
                        .addToBackStack("Dashboard").commit()
                    binding.deawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.logout -> {
                    binding.deawerLayout.closeDrawer(GravityCompat.START)
                    auth.signOut()
                    val intent2 = Intent(this, MainActivity::class.java)
                    startActivity(intent2)
                    finish()
                    Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show()

                }
            }

            return@setNavigationItemSelectedListener true


        }


    }


    fun setsupport() {

        setSupportActionBar(binding.myToolbar) // we're telling toolbar that you work as Actionbar here thats why we use setSupportActionBar
        supportActionBar?.title = "Book-shelf"
        supportActionBar?.setHomeButtonEnabled(true) // enablling button on top of actionbar/toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // displying button on top and after this we'll gonna create the actionBarDrawerToggle
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == android.R.id.home) {
            binding.deawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun defaultFrag() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, Homescreen()).commit()
        binding.deawerLayout.closeDrawers()


    }


    private fun logout() {


    }

    override fun onBackPressed() {
        val frame = supportFragmentManager.findFragmentById(R.id.framelayout)
        when (frame) {

            !is Homescreen -> defaultFrag()
            else -> super.onBackPressed()

        }

    }
}