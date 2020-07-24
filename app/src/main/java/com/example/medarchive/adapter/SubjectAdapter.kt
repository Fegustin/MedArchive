package com.example.medarchive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.medarchive.R
import com.example.medarchive.pojo.FacultySubjects

class SubjectAdapter(private val listArray: List<FacultySubjects>, private val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardViewSubject: CardView = view.findViewById(R.id.cardViewSubject)
        val textViewSubject: TextView = view.findViewById(R.id.textViewSubject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listArray.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listArray[position]

        holder.textViewSubject.text = currentItem.items

        holder.cardViewSubject.setOnClickListener { Toast.makeText(context, "" + currentItem, Toast.LENGTH_SHORT).show() }
    }

}