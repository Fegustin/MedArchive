package com.example.medarchive.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medarchive.R
import com.example.medarchive.adapter.ItemMedAdapter
import com.example.medarchive.pojo.ItemMed
import kotlinx.android.synthetic.main.fragment_list_of_items.*

class ListOfItemsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ArrayList<ItemMed>()

        list.add(ItemMed("Medical biology and general genetics", R.drawable.ic_action))
        list.add(ItemMed("Sociology", R.drawable.ic_action))
        list.add(ItemMed("Анатомия человека", R.drawable.ic_action))
        list.add(ItemMed("Общая химия", R.drawable.ic_action))
        list.add(ItemMed("История медицины", R.drawable.ic_action))
        list.add(ItemMed("Bioorganic chemistry", R.drawable.ic_action))
        list.add(ItemMed("Валеология", R.drawable.ic_action))
        list.add(ItemMed("Биологическая химия", R.drawable.ic_action))

        recyclerViewItemMed.adapter = ItemMedAdapter(list, requireContext())
        recyclerViewItemMed.layoutManager = GridLayoutManager(activity, 2)
        recyclerViewItemMed.setHasFixedSize(true)
    }
}