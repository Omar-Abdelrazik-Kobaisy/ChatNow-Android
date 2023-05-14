package com.example.chat.ui.login

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.chat.base.BaseViewModel
import com.example.chat.db.DataBaseUtils
import com.example.chat.db.models.User
import com.example.chat.ui.UserProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class LoginViewModel : BaseViewModel<Navigator>() {


    var email = ObservableField<String>()
    var emailError = ObservableField<String?>()
    var password = ObservableField<String>()
    var passwordError = ObservableField<String?>()

    var isValid = true

    var auth = Firebase.auth

    fun goToRegister(){
        navigator?.goToRegister()
    }

    fun login()
    {
        Log.e("test-click","clicked")

        if(!validForm())
            return
        else{

            navigator?.showLoading("Loading...")
            auth.signInWithEmailAndPassword(email.get() ?:"" , password.get() ?: "").addOnCompleteListener {
//                navigator?.hideDialog()
                if(it.isSuccessful){
//                    navigator?.showMessage("loggedIn Successfully")
                    getUserFromDB(auth.currentUser?.uid.toString())
//                    Log.e("LogInSuccess",auth.currentUser?.uid.toString())
//                    Log.e("LogInSuccess",auth.currentUser?.email.toString())
                }else {
                    navigator?.hideDialog()
                    navigator?.showMessage(it.exception?.localizedMessage ?: "")
                    Log.e("failToLogin", it.exception?.localizedMessage ?: "")
                }
            }
        }


    }

    private fun getUserFromDB(uid: String) {
        DataBaseUtils().getUser(uid).addOnSuccessListener {documentSnapShot ->
            navigator?.hideDialog()
            val user = documentSnapShot.toObject(User::class.java)
            UserProvider.user  =  user
            navigator?.goToHome()
            Log.e("LogInSuccess",user?.id.toString())
        }.addOnFailureListener {
            navigator?.hideDialog()
            navigator?.showMessage(it.localizedMessage)
        }
    }

    fun validForm():Boolean
    {

        if (email.get().isNullOrBlank())
        {
            isValid = false
            emailError.set("please Enter email")
        }else{
            isValid = true
            emailError.set(null)
        }
        if (password.get().isNullOrBlank())
        {
            isValid = false
            passwordError.set("please Enter password")
        }else{
            isValid = true
            passwordError.set(null)
        }

        return isValid
    }

}