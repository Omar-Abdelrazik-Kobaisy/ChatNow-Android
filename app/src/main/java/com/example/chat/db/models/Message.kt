package com.example.chat.db.models

import android.icu.text.SimpleDateFormat
import android.os.Build
import com.google.firebase.Timestamp

data class Message(
    var id : String? = null,
    var content : String? = null,
    var date : Timestamp? = null,
    var roomId : String? = null,
    var senderId : String? =null,
    var senderName : String? = null,
    var recieverName : String? = null
){
    fun formateDateTime() : String{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("dd/MM/yy hh:mm a").format(date?.toDate())
        } else {
            java.text.SimpleDateFormat("dd/MM/yy hh:mm a").format(date?.toDate())
        }
    }
}
