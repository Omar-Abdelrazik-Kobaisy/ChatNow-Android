package com.example.chat.ui.addroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.base.BaseActivity
import com.example.chat.databinding.ActivityAddRoomBinding
import com.example.chat.ui.addroom.category.CategoriesAdapter
import com.example.chat.ui.addroom.category.Category
import com.example.chat.ui.home.HomeActivity

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding , AddRoomViewModel>() , Navigator {
    lateinit var adapter: CategoriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_room)
        binding.vm = viewModel
        viewModel.navigator = this
        initializeSpinner()
    }

    override fun getLayoutId(): Int = R.layout.activity_add_room

    override fun generateViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

     fun initializeSpinner() {
        adapter = CategoriesAdapter(Category.getCategoryList())
        binding.categorySpinner.adapter = adapter
         binding.categorySpinner.onItemSelectedListener = object :OnItemSelectedListener{
             override fun onItemSelected(
                 parent: AdapterView<*>?,
                 view: View?,
                 position: Int,
                 id: Long
             ) {
                 val selectedCategory = adapter.getItem(position) as Category
                 viewModel.selectedCategory = selectedCategory

             }

             override fun onNothingSelected(parent: AdapterView<*>?) {
                 TODO("Not yet implemented")
             }

         }
    }

    override fun goToHome() {
        startActivity(Intent(this , HomeActivity::class.java))
        finish()
    }
}