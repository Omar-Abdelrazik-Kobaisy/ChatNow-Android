package com.example.chat.base

import androidx.lifecycle.ViewModel

open class BaseViewModel<N:BaseNavigator> : ViewModel(){
    var navigator:N? = null
}