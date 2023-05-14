package com.example.chat.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityHomeBinding
import com.example.chat.db.models.Room
import com.example.chat.ui.ConstantName
import com.example.chat.ui.addroom.AddRoomActivity
import com.example.chat.ui.chat.ChatRoomActivity
import com.example.chat.ui.home.rooms.OnItemClickListener
import com.example.chat.ui.home.rooms.RoomsAdapter
import com.example.chat.ui.login.LoginViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(),Navigator {
    lateinit var adapter: RoomsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        binding.vm = viewModel
        viewModel.navigator = this
        initializeAdapter()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {

        viewModel.bindingRoomsList.observe(this,object :Observer<List<Room?>?>{
            override fun onChanged(rooms: List<Room?>?) {
                adapter.updateList(rooms)
            }

        })
        viewModel.loadRooms()
    }

    fun initializeAdapter(){
        adapter = RoomsAdapter(null)
        binding.contentHome.rooms.adapter = adapter
        adapter.onItemClickListener  =
            OnItemClickListener { position, room ->
                val intent = Intent(this@HomeActivity , ChatRoomActivity::class.java)
                intent.putExtra(ConstantName.Extra_Room, room)
                startActivity(intent)
            }
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun generateViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun openAddRoomActivity() {

        startActivity(Intent(this,AddRoomActivity::class.java))
    }
}