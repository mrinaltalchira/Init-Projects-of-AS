package com.example.bookshelf.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.R
import com.example.bookshelf.model.Bookinfo

class MyAdapterShowall(var context: Context, var itemlist: ArrayList<Bookinfo>) :
    RecyclerView.Adapter<MyAdapterShowall.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_file_layout_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        var currentItem = itemlist[position]

//        holder.bookName.text = currentItem.upbookname
//        holder.autherName.text = currentItem.upauthername
//        holder.bookcategory.text = currentItem.category
//        holder.bookrating.text = currentItem.uprating
        holder.bookName.setText(itemlist[position].upbookname)
        holder.autherName.setText(itemlist[position].upauthername)
        holder.bookcategory.setText(itemlist[position].category)
        holder.bookrating.setText(itemlist[position].uprating)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookName : TextView = view.findViewById(R.id.txt_bookname)
        val autherName : TextView = view.findViewById(R.id.txt_authername)
        val bookcategory : TextView = view.findViewById(R.id.txt_category)
        val bookrating : TextView = view.findViewById(R.id.txt_rating)

    }

}