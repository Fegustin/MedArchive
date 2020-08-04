package com.example.medarchive.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medarchive.R
import com.example.medarchive.adapter.ItemMedAdapter
import com.example.medarchive.pojo.FacultyMed
import kotlinx.android.synthetic.main.fragment_list_of_items.*

class ListOfItemsFragment : Fragment() {

    private var adapter: ItemMedAdapter? = null

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

        val list = ArrayList<FacultyMed>()

        list.addAll(fillArray(resources.getStringArray(R.array.faculty_med)))
        adapter = ItemMedAdapter(list, requireContext())

        recyclerViewItemMed.adapter = adapter
        recyclerViewItemMed.layoutManager = GridLayoutManager(activity, 2)
        recyclerViewItemMed.setHasFixedSize(true)
    }

    private fun fillArray(array: Array<String>): List<FacultyMed> {
        val listArray = ArrayList<FacultyMed>()

        for (element in array) {
            val itemMed = FacultyMed(element)
            listArray.add(itemMed)
        }

        return listArray
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)

        // Search
        val menuItem = menu.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter?.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }

        })
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//            R.id.action_log_out -> {
//                activity?.let {
//                    AuthUI.getInstance()
//                        .signOut(it)
//                        .addOnCompleteListener {
//                            Toast.makeText(activity, "Вы вышли с аккаунта", Toast.LENGTH_SHORT)
//                                .show()
//                            view?.findNavController()?.navigate(R.id.action_global_authFragment)
//                        }
//                }
//                return true
//            }
//        }
//        return false
//    }
}