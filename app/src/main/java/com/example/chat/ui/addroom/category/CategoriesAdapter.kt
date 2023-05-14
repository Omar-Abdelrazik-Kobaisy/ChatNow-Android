package com.example.chat.ui.addroom.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.chat.R
import com.example.chat.databinding.ItemCategoryBinding

class CategoriesAdapter(var items : List<Category>) : BaseAdapter() {


    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // create view Holder
        var view = convertView
        var viewHolder : ViewHolder
        if (view == null) {
            //create view by layoutInflater or data-bindingUtilizes
            var viewBinding: ItemCategoryBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                R.layout.item_category, parent, false
            )
            viewHolder = ViewHolder(viewBinding)
            view = viewHolder.binding.liearLayout
            view.tag = viewHolder
        }else{
            viewHolder = view?.tag as ViewHolder
        }

        //binding view holder

        viewHolder.binding.category = items[position]
        viewHolder.binding.invalidateAll()

        return view

    }

    class ViewHolder(var binding: ItemCategoryBinding)
}