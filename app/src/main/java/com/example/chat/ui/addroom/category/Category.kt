package com.example.chat.ui.addroom.category

import com.example.chat.R

data class Category(
    var id : String,
    var name : String,
    var image : Int
){
    companion object{
        fun getCategoryList() =
            listOf<Category>(
                Category(id = "sports" , name = "Sports" , image = R.drawable.sports ),
                Category(id = "music" , name = "Musics" , image = R.drawable.music ),
                Category(id = "movie" , name = "Movies" , image = R.drawable.movies )

            )

    }
}
