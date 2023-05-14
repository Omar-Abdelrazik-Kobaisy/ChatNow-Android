package com.example.chat.ui.chat

import androidx.databinding.ObservableField
import com.example.chat.base.BaseViewModel
import com.example.chat.db.DataBaseUtils
import com.example.chat.db.models.Message
import com.example.chat.db.models.Room
import com.example.chat.ui.UserProvider
import com.google.firebase.Timestamp

class ChatRoomViewModel:BaseViewModel<Navigator>() {
    var room: Room? = null
    var textMessage = ObservableField<String>()

    fun sendMessage(){
        if(textMessage.get().isNullOrBlank()) return
        val message = Message(content = textMessage.get(), senderId = UserProvider.user?.id , senderName = UserProvider.user?.userName,
        roomId = room?.id, date = Timestamp.now() , recieverName = UserProvider.user?.userName )
        DataBaseUtils().sendMessage(message).addOnCompleteListener {
            if (it.isSuccessful)
            {
                textMessage.set("")

            }else
            {
                navigator?.showMessage("error sending message")
            }
        }
    }
    fun goBack(){
        navigator?.goBack()
    }

}