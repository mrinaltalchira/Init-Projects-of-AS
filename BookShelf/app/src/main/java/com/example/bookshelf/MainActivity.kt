package com.example.bookshelf

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.bookshelf.R.string.close
import com.example.bookshelf.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var navigationView: NavigationView
    lateinit var frameLayout: FrameLayout
    var previousMenuItem: MenuItem? = null
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(getString(R.string.sharedtext), MODE_PRIVATE)
        firebaseAuth = FirebaseAuth.getInstance()
        bottomNavigationView = findViewById(R.id.bottonavigationview)
        drawerLayout = findViewById(R.id.drawerlay)
        coordinatorLayout = findViewById(R.id.coordinatorlay)
        toolbar = findViewById(R.id.my_toolbar)
        navigationView = findViewById(R.id.nav_view)
        frameLayout = findViewById(R.id.frame_lay)


        setsupport() // calling Actionbar
        default_frag()

        val actionBarDrawerToggle =
            ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                R.string.open,
                close
            ) // preparing action bar drawer toggle



        drawerLayout.addDrawerListener(actionBarDrawerToggle) // putting drawer toggle into drawerlayout
        actionBarDrawerToggle.syncState() // syncing state of toggl
        // by this sync we allow the toggle/hamburgericon  that if state if oprn than create back sign and if is in
        // then create that hamburger icon


        navigationView.setNavigationItemSelectedListener {              //putting click listner on Navigationbar

            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }
            it.isChecked = true
            it.isCheckable = true
            previousMenuItem = it

            when (it.itemId) {
                R.id.men_profile -> {
                    startfrag(Profile())
                    drawerLayout.closeDrawers()
                    supportActionBar?.title = "Profile"

                }
                R.id.men_aboutus -> {

                    startfrag(Aboutus())
                    supportActionBar?.title = "About us"
                    drawerLayout.closeDrawers()
                }
                R.id.men_help -> {
                    startfrag(Help())
                    supportActionBar?.title = "Help center"
                    drawerLayout.closeDrawers()
                }
                R.id.men_logout -> {

                    firebaseAuth.signOut()
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    sharedPreferences.edit().putBoolean("islogin", false).apply()
                    finish()
                }
            }
            return@setNavigationItemSelectedListener true
        }


        bottomNavigationView.setOnNavigationItemSelectedListener {

            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }
            it.isChecked = true
            it.isCheckable = true
            previousMenuItem = it

            when (it.itemId) {
                R.id.menu_newfeed -> {
                    startfrag(Newdata())
                    supportActionBar?.title = "New Data"
                }
                R.id.menu_edtrecord -> {
                    startfrag(Editdata())
                    supportActionBar?.title = "Edit Data"
                }
                R.id.menu_showall -> {
                    startfrag(Fragone())
                    supportActionBar?.title = "All Data"
                }

            }

            return@setOnNavigationItemSelectedListener true
        }


    }


    fun setsupport() {

        setSupportActionBar(toolbar) // we're telling toolbar that you work as Actionbar here thats why we use setSupportActionBar
        supportActionBar?.setHomeButtonEnabled(true) // enablling button on top of actionbar/toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // displying button on top and after this we'll gonna create the actionBarDrawerToggle
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun default_frag() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_lay, Newdata()).commit()

        drawerLayout.closeDrawers()
        supportActionBar?.title = "New Data"
//        navigationView.setCheckedItem(R.id.men_option1)
    }

    override fun onBackPressed() {
        val frame = supportFragmentManager.findFragmentById(R.id.frame_lay)
        when (frame) {

            !is Fragone -> default_frag()
            else -> super.onBackPressed()

        }
    }


    fun startfrag(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .apply { addToBackStack("fragname").replace(R.id.frame_lay, fragment).commit() }
    }


}