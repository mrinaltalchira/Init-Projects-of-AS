package com.example.studentmanagement.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.location.GnssAntennaInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Header
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.studentmanagement.R
import com.example.studentmanagement.adapter.Dashboard_adapter
import com.example.studentmanagement.model.Books
import com.example.studentmanagement.util.ConnectionManager
import com.google.firebase.database.*
import com.google.firebase.firestore.auth.User
import okhttp3.Request
import okhttp3.Response


class Homescreen : Fragment() {

    lateinit var dbref: DatabaseReference
    lateinit var recyclerView: RecyclerView
    lateinit var userArrayList: ArrayList<Books>
    lateinit var dashboardAdapter: Dashboard_adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_homescreen, container, false)


      //  dashboardAdapter = Dashboard_adapter(activity as Context, userArrayList)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<Books>()
        getUserData()

        // recyclerView.adapter = dashboardAdapter

        // recyclerView.layoutManager = layoutManager
//
//        recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                recyclerView.context,
//                (layoutManager as LinearLayoutManager).orientation
//            )
//        )


//        conectionbtn.setOnClickListener {
//            if (ConnectionManager().checkConnectivity(activity as Context)) {
//
//                val dialog = AlertDialog.Builder(activity as Context)
//                dialog.setTitle("Success")
//                dialog.setMessage("Internet connection is found")
//                dialog.setPositiveButton("ok") { text, listner -> // do nothing
//                }
//                dialog.setNegativeButton("cancel") { text, listner -> // do nothing}
//                }
//                dialog.create()
//                dialog.show()
//            } else {
//                val dialog = AlertDialog.Builder(activity as Context)
//                dialog.setTitle("Error")
//                dialog.setMessage("Internet connection not found")
//                dialog.setPositiveButton("ok") { text, listner -> // do nothing
//                }
//                dialog.setNegativeButton("cancel") { text, listner -> // do nothing}
//                }
//                dialog.create()
//                dialog.show()
//            }
//        }


//        val queue = Volley.newRequestQueue(activity as Context)
//        val url = "http://13.235.250.119/v1/book/fetch_books/"
//        val jsonObjectRequest =
//            object : JsonObjectRequest(Method.GET, url, null, com.android.volley.Response.Listener {
//
//            },
//                com.android.volley.Response.ErrorListener {
//
//                }) {
//                override fun getHeaders(): MutableMap<String, String> {
//                    val Header = HashMap<String, String>()
//                    headers["content-type"] = "application/json"
//                    headers["token"] = "6145af826597d0"
//
//                    return headers
//                }
//
//            }


        return view

    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("book")
        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {

                        val books = userSnapshot.getValue(Books::class.java)
                        userArrayList.add(books!!)
                    }

                    recyclerView.adapter = Dashboard_adapter(activity as Context, userArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

}
