package com.example.chat.db.models

import android.os.Parcelable
import com.example.chat.R
import kotlinx.parcelize.Parcelize


@Parcelize
data class Room (
    var id : String? = null,
    var title : String? = null,
    var description : String? = null,
    var categoryId : String? = null,
    var createdBy : String? = null
        ) : Parcelable {
    fun getImageId() : Int{
        return when(categoryId){
            "sports"-> R.drawable.sports
            "music"-> R.drawable.music
            "movie"-> R.drawable.movies
            else -> {0}
        }

    }
}