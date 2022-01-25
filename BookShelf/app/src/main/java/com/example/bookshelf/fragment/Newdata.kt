package com.example.bookshelf.fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.bookshelf.R
import com.example.bookshelf.model.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class Newdata : Fragment() {


    var imageUri: Uri? = null
    var storage = Firebase.storage
    val database = Firebase.database
    var myRef = database.getReference("booksdata")
    lateinit var uprating: String
    lateinit var upbookname: String
    lateinit var upauthername: String
    lateinit var ratingBar: RatingBar
    lateinit var buttonupdate: Button
    lateinit var category: String
    lateinit var imageView: ImageView
    lateinit var button: FloatingActionButton
    lateinit var spinner: Spinner
    lateinit var auther: EditText
    lateinit var bookname: EditText
    lateinit var dialog: Dialog


    companion object {
        val IMAGETOKEN = 200
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_newdata, container, false)


        ratingBar = view.findViewById(R.id.edt_rating)
        imageView = view.findViewById(R.id.imageView)
        button = view.findViewById(R.id.btn_img_pick)
        spinner = view.findViewById(R.id.spinner)
        buttonupdate = view.findViewById(R.id.btn_update)
        bookname = view.findViewById(R.id.edt_bookname)
        auther = view.findViewById(R.id.edt_authername)





        buttonupdate.setOnClickListener {
            uprating = ratingBar.rating.toString()
            upbookname = bookname.text.toString()
            upauthername = auther.text.toString()

            if (uprating != null && upauthername != null && upbookname != null && imageUri != null) {
                showProgressBar()


                val user = User(category, upbookname, upauthername, uprating)

                myRef.child(category).child(upbookname).setValue(user).addOnCompleteListener {

                    if (it.isSuccessful) {
                        bookname.text.clear()
                        auther.text.clear()
                        uploadProfilePic()
                        Toast.makeText(
                            requireContext(),
                            "Uploaded successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        hideProgressbar()
                        Toast.makeText(requireContext(), "Failed to update", Toast.LENGTH_SHORT)
                            .show()
                    }
                }


            } else {
                Toast.makeText(activity, "please enter all the above details", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        button.setOnClickListener {

            pickImageGaallary()

        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                category = adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        return view
    }

    private fun uploadProfilePic() {

        val store = storage.getReference("bookImage/" + upbookname)
        imageUri?.let { store.putFile(it) }?.addOnSuccessListener {
            hideProgressbar()
            Toast.makeText(requireContext(), "profile uploaded", Toast.LENGTH_SHORT).show()

        }?.addOnFailureListener {
            hideProgressbar()
            Toast.makeText(requireContext(), "Failed to upload profile", Toast.LENGTH_SHORT).show()
        }

    }


    private fun pickImageGaallary() {
//
//        var intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, IMAGETOKEN)
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, IMAGETOKEN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        try {
            if (requestCode == IMAGETOKEN) {

//            imageView.setImageURI(data?.data)
                imageUri = data!!.data
                imageView.setImageURI(imageUri)

            }

            super.onActivityResult(requestCode, resultCode, data)
        } catch (ex: Exception) {
            Toast.makeText(activity, "Canceled", Toast.LENGTH_SHORT).show()
        }
    }

    fun showProgressBar() {
        dialog = Dialog(activity as Context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.progressbaar)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

    }

    fun hideProgressbar() {

        dialog.dismiss()

    }

}




