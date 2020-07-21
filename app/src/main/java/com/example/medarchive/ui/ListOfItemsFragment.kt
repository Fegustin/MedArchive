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

        list.add(ItemMed("bio", R.drawable.common_full_open_on_phone))
        list.add(ItemMed("bio1", R.drawable.common_full_open_on_phone))
        list.add(ItemMed("bi2", R.drawable.common_full_open_on_phone))
        list.add(ItemMed("bi3", R.drawable.common_full_open_on_phone))
        list.add(ItemMed("bi4", R.drawable.common_full_open_on_phone))
        list.add(ItemMed("bi5", R.drawable.common_full_open_on_phone))
        list.add(ItemMed("bi6", R.drawable.common_full_open_on_phone))
        list.add(ItemMed("bi7", R.drawable.common_full_open_on_phone))

        recyclerViewItemMed.adapter = ItemMedAdapter(list, requireContext())
        recyclerViewItemMed.layoutManager = GridLayoutManager(activity, 2)
        recyclerViewItemMed.setHasFixedSize(true)
    }
}