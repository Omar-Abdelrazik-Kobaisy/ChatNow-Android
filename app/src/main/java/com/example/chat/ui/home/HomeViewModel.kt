package com.example.chat.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.chat.base.BaseViewModel
import com.example.chat.db.DataBaseUtils
import com.example.chat.db.models.Room

class HomeViewModel: BaseViewModel<Navigator>() {

    var bindingRoomsList = MutableLiveData<List<Room?>?>()

    fun addRoom(){
        // open activity to add room
    navigator?.openAddRoomActivity()
    }
    fun loadRooms(){
        navigator?.showLoading("Loading....")
        DataBaseUtils().getRoom().addOnCompleteListener {
            navigator?.hideDialog()
            if(it.isSuccessful)
            {
                var rooms = it.result.toObjects(Room::class.java)
                bindingRoomsList.value = rooms
            }else{
                navigator?.showMessage(it.exception?.localizedMessage ?: "")
            }
        }
    }
}