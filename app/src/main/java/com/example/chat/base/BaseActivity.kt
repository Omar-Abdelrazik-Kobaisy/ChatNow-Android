package com.example.chat.base

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VB:ViewDataBinding , VM:ViewModel> : AppCompatActivity() , BaseNavigator {

    lateinit var binding : VB
    lateinit var viewModel : VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , getLayoutId() )
        viewModel = generateViewModel()
    }
    abstract fun getLayoutId():Int;
    abstract fun generateViewModel():VM;

    var alertDialog : AlertDialog? = null
    var progressDialog : ProgressDialog? = null
    override fun showLoading(message: String) {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage(message)
        progressDialog?.show()
    }

    override fun hideDialog() {

        alertDialog?.dismiss()
        progressDialog?.dismiss()
        progressDialog = null
    }

    override fun showMessage(message: String , positiveAction : OnDialogClickListener?) {

        alertDialog = AlertDialog.Builder(this).setMessage(message).setPositiveButton("ok"
            ,object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    positiveAction?.onDialogClick()
                    dialog?.dismiss()

                }

            }).show()
    }

}



