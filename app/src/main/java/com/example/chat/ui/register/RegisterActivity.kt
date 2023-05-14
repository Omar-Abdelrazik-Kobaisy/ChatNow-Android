package com.example.chat.ui.register

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.base.BaseNavigator
import com.example.chat.databinding.ActivityRegisterBinding
import com.example.chat.ui.home.HomeActivity
import com.example.chat.ui.login.LoginActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding , RegisterViewModel>(),
    Navigator {
//    lateinit var binding: ActivityRegisterBinding
//    lateinit var viewModel: RegisterViewModel
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }
    override fun generateViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
//        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.viewm = viewModel


        viewModel.navigator = this // with out this line navigator will be null
    }

    override fun goToLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    override fun goToHome() {
        startActivity(Intent(this,HomeActivity::class.java))
    }


}