package com.example.chat.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chat.base.BaseViewModel
import com.example.chat.db.DataBaseUtils
import com.example.chat.db.models.User
import com.example.chat.isValidEmail
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : BaseViewModel<Navigator>() {

//    var navigator : Navigator? = null

    var userName = ObservableField<String>()
    var userNameError = ObservableField<String?>()
    var email = ObservableField<String>()
    var emailError = ObservableField<String?>()
    var password = ObservableField<String>()
    var passwordError = ObservableField<String?>()
    var confirmPassword = ObservableField<String>()
    var confirmPasswordError = ObservableField<String?>()
    var isValid = true

    var auth = Firebase.auth

    fun register()
    {

        if(!validForm())
            return
        navigator?.showLoading("loading...")
        auth.createUserWithEmailAndPassword(email.get() ?: "" , password.get() ?: "").addOnCompleteListener {
//            navigator?.hideDialog()
            if (it.isSuccessful)
            {
                // user created successfully
//                navigator?.showMessage("successfully registered")

                insertUserToDB(it.result.user?.uid ?: "")
//                Log.e("UID" , it.result.user?.uid ?: "")
            }else
            {
                navigator?.hideDialog()
                navigator?.showMessage(it.exception?.localizedMessage ?: "")
//                Log.e("error" , it.exception?.localizedMessage ?: "")
            }
        }


    }

    private fun insertUserToDB(uid:String) {

        val user = User(id = uid , userName = userName.get() , email = email.get())
        DataBaseUtils().addUser(user)
            .addOnSuccessListener {
                navigator?.hideDialog()
                navigator?.goToHome()
                Log.e("loggedIn Successfully", user.id.toString()) }
            .addOnFailureListener {
                navigator?.hideDialog()
                Log.e("LoggedIn Fail",it.localizedMessage) }
    }

    fun validForm():Boolean
    {

        if (userName.get().isNullOrBlank())
        {
            isValid = false
            userNameError.set("please Enter user name")
        }else{
            isValid = true
            userNameError.set(null)
        }
        if (email.get().isNullOrBlank())
        {
            isValid = false
            emailError.set("please Enter email")
        }else if (email.get()?.isValidEmail() == false)
        {
            isValid = false
            emailError.set("not valid email")
        }

        else{
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
        if (confirmPassword.get().isNullOrBlank() ||
            confirmPassword.get() != password.get()   )
        {
            isValid = false
            confirmPasswordError.set("ReEnter password")
        }else{
            isValid = true
            confirmPasswordError.set(null)
        }

        return isValid
    }

    fun goToLogin(){
        navigator?.goToLogin()
    }

}