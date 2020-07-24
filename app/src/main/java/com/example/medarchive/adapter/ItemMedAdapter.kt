package com.example.medarchive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medarchive.R
import com.example.medarchive.pojo.ItemMed
import com.example.medarchive.ui.ListOfItemsFragmentDirections

class ItemMedAdapter(private val listArray: List<ItemMed>, private val context: Context) :
    RecyclerView.Adapter<ItemMedAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layoutMed: ConstraintLayout = view.findViewById(R.id.layoutMed)
        val textViewNameItemMed: TextView = view.findViewById(R.id.textViewNameItemMed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_med_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listArray.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listArray[position]

        holder.textViewNameItemMed.text = currentItem.faculty

<<<<<<< HEAD
        holder.layoutMed.setOnClickListener {
//            val action = ListOfItemsFragmentDirections
=======
        // Event
        holder.layoutMed.setOnClickListener {
            val action = ListOfItemsFragmentDirections.actionListOfItemsFragmentToSubjectsFragment(currentItem.faculty)
            it.findNavController().navigate(action)
>>>>>>> 2d9c416... Добавил факультеты и предметы к ним
        }
    }
}