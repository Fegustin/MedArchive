package com.example.medarchive.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medarchive.R
import com.example.medarchive.adapter.ItemMedAdapter
import com.example.medarchive.pojo.ItemMed
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.fragment_list_of_items.*

class ListOfItemsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ArrayList<ItemMed>()

        list.addAll(fillArray(resources.getStringArray(R.array.faculty_med)))

        recyclerViewItemMed.adapter = ItemMedAdapter(list, requireContext())
        recyclerViewItemMed.layoutManager = GridLayoutManager(activity, 2)
        recyclerViewItemMed.setHasFixedSize(true)
    }

    private fun fillArray(array: Array<String>): List<ItemMed> {
        val listArray = ArrayList<ItemMed>()

        for (element in array) {
            val itemMed = ItemMed(element)
            listArray.add(itemMed)
        }

        return listArray
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_log_out -> {
                activity?.let {
                    AuthUI.getInstance()
                        .signOut(it)
                        .addOnCompleteListener {
                            Toast.makeText(activity, "Вы вышли с аккаунта", Toast.LENGTH_SHORT)
                                .show()
                            view?.findNavController()?.navigate(R.id.action_global_authFragment)
                        }
                }
                return true
            }
        }
        return false
    }
}