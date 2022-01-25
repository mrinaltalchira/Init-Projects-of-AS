package com.example.bookshelf

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.adapter.MyAdapterShowall
import com.example.bookshelf.model.Bookinfo
import com.example.bookshelf.model.Foodinfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

//
class Fragone : Fragment() {

//    lateinit var recyclerView: RecyclerView
//    lateinit var bookArray: ArrayList<Bookinfo>
//    private var database = Firebase.database

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)

//        recyclerView = view.findViewById(R.id.recycle_view)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.setHasFixedSize(true)
//
//        bookArray = arrayListOf<Bookinfo>()
//        getBookData()






        return view
    }
//
//    private fun getBookData() {
//        Toast.makeText(requireContext(), "in get nook functikon", Toast.LENGTH_SHORT).show()
//        database.getReference("booksdata").child("").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                Toast.makeText(requireContext(), "in add value", Toast.LENGTH_SHORT).show()
//
//                if (snapshot.exists()) {
//                    for (dataSnapshot in snapshot.children) {
//
//
//                        var booksinfo = dataSnapshot.getValue(Bookinfo::class.java)
//
//
//                         bookArray.add(booksinfo!!)
//                        Toast.makeText(requireContext(), "infor loop"+booksinfo.toString(), Toast.LENGTH_SHORT).show()
//
//                    }
//                    recyclerView.adapter = MyAdapterShowall(requireContext(), bookArray)
//                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
//                    recyclerView.setHasFixedSize(true)
//
//                }
//                Toast.makeText(requireContext(), "youre outside", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(requireContext(), "cancel", Toast.LENGTH_SHORT).show()
//
//            }
//
//
//        })
//
//    }
}