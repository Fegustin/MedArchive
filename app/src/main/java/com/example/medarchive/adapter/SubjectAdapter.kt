package com.example.medarchive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.medarchive.R
import com.example.medarchive.pojo.FacultySubjects
import java.util.*
import kotlin.collections.ArrayList

class SubjectAdapter(private val listArray: List<FacultySubjects>, private val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>(), Filterable {

    private var searchList: List<FacultySubjects> = listArray

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardViewSubject: CardView = view.findViewById(R.id.cardViewSubject)
        val textViewSubject: TextView = view.findViewById(R.id.textViewSubject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = searchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = searchList[position]

        holder.textViewSubject.text = currentItem.items

        holder.cardViewSubject.setOnClickListener {
            Toast.makeText(
                context,
                "" + currentItem,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                searchList = if (p0.toString().isEmpty()) {
                    listArray
                } else {
                    val resultList = ArrayList<FacultySubjects>()
                    val filterPattern = p0.toString().toLowerCase(Locale.ROOT).trim()

                    for (i in listArray) {
                        if (i.items.toLowerCase(Locale.ROOT).contains(filterPattern)) {
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
                searchList = p1?.values as List<FacultySubjects>
                notifyDataSetChanged()
            }

        }
    }

}