package com.example.chat.ui.chat

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityChatRoomBinding
import com.example.chat.db.DataBaseUtils
import com.example.chat.db.models.Message
import com.example.chat.db.models.Room
import com.example.chat.ui.ConstantName
import com.google.firebase.firestore.ListenerRegistration

class ChatRoomActivity : BaseActivity<ActivityChatRoomBinding , ChatRoomViewModel>() , Navigator {
    var room: Room? = null
    var adapter: MessageAdapter = MessageAdapter(mutableListOf())

    var listener : ListenerRegistration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_chat_room)
        binding.vm = viewModel
        viewModel.navigator = this
        initializeRoom()
//        subscribToMessageChange()
        initializeMesageAdapter()
    }

    private fun initializeMesageAdapter() {
//        adapter = MessageAdapter(messages)
        binding.messagesRecycerView.adapter = adapter
//        adapter.updateMessage(messages)
    }

    private fun subscribToMessageChange() {
        listener = DataBaseUtils().getRoomMessages(room?.id ?:"").addSnapshotListener { value , e->
            if (e != null)
            {
                showMessage(e.localizedMessage)
            }else{

                //you need message object not only the content of message so try to use DocumentQuerySnapShoot
//                for (doc in value!!)
//                {
//                    val message = doc.toObject(Message::class.java)
////                    messages.add(message)
//                    adapter.addMessage(message)
//                    }
                // cuz collection have documents (messages) every time you send message (documentChange)
                //add this message to adapter
                value?.documentChanges?.forEach {
                    val message = it.document.toObject(Message::class.java)
                    adapter.addMessage(message)
                    binding.messagesRecycerView.smoothScrollToPosition(adapter.messages.size)
                }
                }
            }
        }

    override fun onStart() {
        super.onStart()
        if (listener == null)
        {
            subscribToMessageChange()
        }
    }

    override fun onStop() {
        super.onStop()
        listener?.remove()
        listener = null
    }


    private fun initializeRoom() {
        room = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(ConstantName.Extra_Room,Room::class.java)
        }else{
            intent.getParcelableExtra(ConstantName.Extra_Room)
        }
        viewModel.room = room
    }

    override fun getLayoutId(): Int = R.layout.activity_chat_room

    override fun generateViewModel(): ChatRoomViewModel {
        return ViewModelProvider(this).get(ChatRoomViewModel::class.java)
    }

    override fun goBack() {
        finish()
    }
}