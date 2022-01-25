package com.example.studentmanagement.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import com.example.studentmanagement.R
import com.example.studentmanagement.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

      var RC_SIGN_IN = 101
    private lateinit var googleSignInClient: GoogleSignInClient
    lateinit var binding: ActivityMainBinding // binding main activity view
    private lateinit var auth: FirebaseAuth
    lateinit var sharePref: SharedPreferences

  override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null)
        { startActivity(Intent(this,Screen_one::class.java))}
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        //innitilizing sharedprefe. object
        sharePref = getSharedPreferences(getString(R.string.sharedtext), MODE_PRIVATE)

        super.onCreate(savedInstanceState)

        // inflating binding view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth



        var login = sharePref.getBoolean("islogin", false)
        if (login) {

            val intent = Intent(this, Screen_one::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }


        binding.btnGgl.setOnClickListener {
            createRequest()
            processSignin()


        }

        binding.txtSignup.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
            finish()
        }

        binding.forgetpass.setOnClickListener {
            val intent = Intent(this, Forgetpass::class.java)
            startActivity(intent)

        }


    }




    private fun updateUI(currentUser: FirebaseUser?) {

    }

    fun login() {


            val password = binding.loginPass.text.toString()
            val userName = binding.loginEmail.text.toString()


            if (password.isEmpty() || userName.isEmpty()) {
                Toast.makeText(this, "please enter details", Toast.LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(this) {

                        task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, Screen_one::class.java)
                        savedata()
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                    }


                }
            }




    }


    fun savedata(){
        sharePref.edit().putBoolean("islogin", true).apply()

    }

// google login start

    fun createRequest(){
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    fun processSignin() {
        val signInIntent = googleSignInClient.signInIntent

        startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int , data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)

            } catch (e: ApiException) {
                Toast.makeText(this,"sorry ! data not recived",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    Toast.makeText(this,"successfully login",Toast.LENGTH_SHORT).show()

                    val user = auth.currentUser
                    var intent1 = Intent(this,Screen_one::class.java)
                    startActivity(intent1)
                    finish()


                } else {


                    Toast.makeText(this,"sorry ! login failed",Toast.LENGTH_SHORT).show()

                }
            }
    }

// end google login


    override fun onPause() {
        finish()
        super.onPause()
    }


    }

