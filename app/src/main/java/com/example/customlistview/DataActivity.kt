package com.example.customlistview

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)


        btn_add.setOnClickListener {
            val name =fb_name.text.toString()
            val  nummber =  fb_number.text.toString()
            val address = fb_address.text.toString()
            val mProgressDialog = ProgressDialog(this)
            mProgressDialog.setTitle("This is TITLE")
            mProgressDialog.setMessage("Waiting for the data to be sent ")
            mProgressDialog.show()

            val person = hashMapOf(
                "name" to name,
                "nummber" to nummber,
                "address" to address
            )
            db.collection("persons")
                .add(person)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this,"${documentReference.id}", Toast.LENGTH_SHORT).show()
                    mProgressDialog.cancel()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this,"$e", Toast.LENGTH_SHORT).show()
                }
        }

        btn_data.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
}