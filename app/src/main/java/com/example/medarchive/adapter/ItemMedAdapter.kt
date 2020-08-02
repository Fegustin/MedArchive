package com.example.medarchive.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medarchive.R
import com.example.medarchive.pojo.ItemMed
import com.example.medarchive.ui.ListOfItemsFragmentDirections
import java.util.*
import kotlin.collections.ArrayList

class ItemMedAdapter(private val listArray: List<ItemMed>, private val context: Context) :
    RecyclerView.Adapter<ItemMedAdapter.ViewHolder>(), Filterable {

    private var searchList: List<ItemMed> = listArray

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layoutMed: ConstraintLayout = view.findViewById(R.id.layoutMed)
        val textViewNameItemMed: TextView = view.findViewById(R.id.textViewNameItemMed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_med_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = searchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = searchList[position]

        holder.textViewNameItemMed.text = currentItem.faculty

        holder.layoutMed.setOnClickListener {
            val action = ListOfItemsFragmentDirections.actionListOfItemsFragmentToSubjectsFragment(
                currentItem.faculty
            )
            it.findNavController().navigate(action)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                searchList = if (p0.toString().isEmpty()) {
                    listArray
                } else {
                    val resultList = ArrayList<ItemMed>()
                    val filterPattern = p0.toString().toLowerCase(Locale.ROOT).trim()

                    for (i in listArray) {
                        if (i.faculty.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            resultList.add(i)
                        }
                    }

                    resultList
                }

                val filterResult = FilterResults()
                filterResult.values = searchList
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                searchList = p1?.values as List<ItemMed>
                notifyDataSetChanged()
            }

        }
    }
}