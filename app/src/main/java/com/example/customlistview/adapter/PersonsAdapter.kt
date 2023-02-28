package com.example.customlistview.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.customlistview.R
import com.example.customlistview.modle.Persons
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fruit_item.view.*

class PersonsAdapter(var activity: Activity, var data : ArrayList<Persons>): BaseAdapter() {
    val db = Firebase.firestore

    override fun getCount(): Int {
      return  data.size
    }

    override fun getItem(position: Int): Any {
        return  data[position]
    }

    override fun getItemId(position: Int): Long {
        return  data[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var root = convertView
        if(root == null)
            root = LayoutInflater.from(activity).inflate(R.layout.fruit_item,null,false)
        root!!.fb_name.text = data[position].name
        root.fb_number.text = data[position].nummber
        root.txt_address.text = data[position].adress





        return  root
    }
}