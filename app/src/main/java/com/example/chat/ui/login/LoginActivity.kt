package com.example.chat.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityLoginBinding
import com.example.chat.ui.home.HomeActivity
import com.example.chat.ui.register.RegisterActivity

class LoginActivity : BaseActivity<ActivityLoginBinding , LoginViewModel>(), Navigator {
//    lateinit var binding: ActivityLoginBinding
//    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
//        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.viewm = viewModel
        viewModel.navigator = this
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun generateViewModel(): LoginViewModel {

        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun goToRegister() {
        startActivity(Intent(this,RegisterActivity::class.java))
        finish()
    }

    override fun goToHome() {
        startActivity(Intent(this,HomeActivity::class.java))
    }
}