package com.example.chat.db

import android.util.Log
import com.example.chat.db.models.Message
import com.example.chat.db.models.Room
import com.example.chat.db.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataBaseUtils {

    private val db = Firebase.firestore
    private val usersCollectionName = "users"
    private val roomsCollectionName = "rooms"
    private val messagesCollectionName = "messages"
    fun addUser(user: User) : Task<Void> {
        return db.collection(usersCollectionName).document(user.id ?: "").set(user)
    }
    fun getUser(uid:String) : Task<DocumentSnapshot>{
        return db.collection(usersCollectionName).document(uid).get()
    }

    fun addRoom(room : Room) : Task<Void>{

        val docRef = db.collection(roomsCollectionName).document()
        Log.e("docRefId",docRef.id)
        room.id = docRef.id
        return docRef.set(room)
    }

    fun  getRoom() :Task<QuerySnapshot>{
        return db.collection(roomsCollectionName).get()
    }

    fun sendMessage(message : Message) : Task<Void>{
        val roomDocRef = db.collection(roomsCollectionName).document(message.roomId ?: "")
        val messagesDocRef = roomDocRef.collection(messagesCollectionName).document()
        message.id = messagesDocRef.id
        return messagesDocRef.set(message)
    }
    fun getRoomMessages(roomId :String) : Query{
        return db.collection(roomsCollectionName).document(roomId ?: "")
            .collection(messagesCollectionName).orderBy("date")
    }
}