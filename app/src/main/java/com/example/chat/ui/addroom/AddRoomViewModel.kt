package com.example.chat.ui.addroom

import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.example.chat.base.BaseViewModel
import com.example.chat.db.DataBaseUtils
import com.example.chat.db.models.Room
import com.example.chat.ui.UserProvider
import com.example.chat.ui.addroom.category.Category

class AddRoomViewModel : BaseViewModel<Navigator>() {

    var selectedCategory: Category = Category.getCategoryList().get(0)
    var title = ObservableField<String>()
    var titleError = ObservableField<String?>()
    var description = ObservableField<String>()
    var descriptionError = ObservableField<String?>()
    var isValid : Boolean = false

    fun createRoom(){

        if(!Validate())
            return

        insertRoomToDataBase()

    }

    private fun insertRoomToDataBase() {
        navigator?.showLoading("Loading ....")
        val room = Room(
            title = title.get() ?:"",
            description = description.get() ?: "" ,
            categoryId = selectedCategory.id ,
            createdBy = UserProvider.user?.id ?:"")
        DataBaseUtils().addRoom(room).addOnCompleteListener {
            navigator?.hideDialog()
            if (it.isSuccessful)
            {
                navigator?.showMessage("Room Created Successfully") {
                    navigator?.goToHome()
                }


            }else{
                navigator?.showMessage(it.exception?.localizedMessage ?: "")
            }
        }
    }

    fun Validate() : Boolean{
        isValid = true
        if (title.get().isNullOrBlank())
        {
            isValid = false
            titleError.set("please enter group name")
        }
        return isValid
    }

}