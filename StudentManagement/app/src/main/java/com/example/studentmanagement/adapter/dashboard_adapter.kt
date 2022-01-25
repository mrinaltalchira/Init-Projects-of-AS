package com.example.studentmanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagement.R
import com.example.studentmanagement.model.Books

class Dashboard_adapter(val context: Context, val itemlist: ArrayList<Books>) :
    RecyclerView.Adapter<Dashboard_adapter.DashboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_view, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val currentItem = itemlist[position]
        holder.bookname.text = currentItem.bookname
        holder.rating.text = currentItem.rating
        holder.authername.text = currentItem.authername
       // holder.bookimg.setImageResource(currentItem.bookimg)
        holder.authername.text = currentItem.bookimg

        holder.layout.setOnClickListener {
            Toast.makeText(context, "clicked on ${holder.bookname}", Toast.LENGTH_SHORT).show()


        }


    }

    override fun getItemCount(): Int {
        return itemlist.size
    }


    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val bookname: TextView = view.findViewById(R.id.txt_bookname)
        val bookimg: ImageView = view.findViewById(R.id.img_roundedbookimg)
        val rating: TextView = view.findViewById(R.id.txt_rating)
        val authername: TextView = view.findViewById(R.id.txt_authername)
        val layout: ConstraintLayout = view.findViewById(R.id.recyclerviewConstraintlayput)

    }

}