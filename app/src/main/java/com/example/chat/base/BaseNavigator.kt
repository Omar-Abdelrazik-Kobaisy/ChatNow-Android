package com.example.chat.base

interface BaseNavigator{

    fun showLoading(message : String);

    fun hideDialog();

    fun showMessage(message: String , positiveAction : OnDialogClickListener? = null);
}