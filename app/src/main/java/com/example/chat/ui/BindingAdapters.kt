package com.example.chat.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorMessage")
fun bindingErrorMessage(textInputLayout:TextInputLayout , errorMessage : String?)
{
    textInputLayout.error = errorMessage
}
@BindingAdapter("categoryTitle")
fun bindingtitleCategory(textView : TextView , title : String)
{
    textView.text = title
}
@BindingAdapter("imageId")
fun bindingImageCategory(imageView: ImageView , imageId : Int)
{
    imageView.setImageResource(imageId)
}