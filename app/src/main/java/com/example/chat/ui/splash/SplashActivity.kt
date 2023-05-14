package com.example.chat.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.chat.R
import com.example.chat.db.DataBaseUtils
import com.example.chat.db.models.User
import com.example.chat.ui.UserProvider
import com.example.chat.ui.home.HomeActivity
import com.example.chat.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(object :Runnable{
            override fun run() {
                navigate()
//                goToLogin()
            }

        },3000)
    }

    private fun navigate() {
        val auth = Firebase.auth
        if(auth.currentUser == null)
        {
            goToLogin()
            return
        }
        print(auth.currentUser?.email)
        Log.e("testAuth",auth.currentUser?.email ?: "")
        DataBaseUtils().getUser(auth.uid.toString()).addOnCompleteListener {

            if(it.isSuccessful)
            {
                val user = it.result.toObject(User::class.java)
                Log.e("userEmail" , user?.id ?: "")
                UserProvider.user = user
                goToHome()
            }else{
                print(it.exception?.localizedMessage )
                goToLogin()
            }
        }

    }

    private fun goToHome() {startActivity(Intent(this,HomeActivity::class.java))
    }

    private fun goToLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
    }
}