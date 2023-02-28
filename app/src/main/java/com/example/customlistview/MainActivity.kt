package com.example.customlistview

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.customlistview.adapter.PersonsAdapter
import com.example.customlistview.modle.Persons
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var name : String
        var adress : String
        var num : String
        val arr = ArrayList<Persons>()


        var arryAdapter = PersonsAdapter(this,arr)

        val mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("get data")
        mProgressDialog.setMessage("Waiting for get data")
        mProgressDialog.show()


        db.collection("persons").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    for (dc in document ){
                        name = dc.get("name").toString()
                        adress = dc.get("address").toString()
                        num = dc.get("nummber").toString()
                        arr.add(Persons(1,name, num,adress))
                        mProgressDialog.cancel()

                    }
                    fruit_list.adapter = arryAdapter

                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }









//        fruit_list.setOnItemClickListener { _, _, position, _ ->
//            Toast.makeText(this,"${arr[position].id}",Toast.LENGTH_SHORT).show()
//        }
//
//        fruit_list.setOnItemLongClickListener { _, _, position, _ ->
//            Toast.makeText(this,"${arr[position].name} Long",Toast.LENGTH_SHORT).show()
//
//            true
//        }
    }
}