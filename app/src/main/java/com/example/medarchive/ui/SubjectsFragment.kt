package com.example.medarchive.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medarchive.R
import com.example.medarchive.adapter.SubjectAdapter
import com.example.medarchive.pojo.FacultySubjects
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.fragment_subjects.*


class SubjectsFragment : Fragment() {

    private var adapter: SubjectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subjects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.let { SubjectsFragmentArgs.fromBundle(it) }

        val list = ArrayList<FacultySubjects>()

        when(args?.subject){
            "Факульет1" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_1))) }
            "Факульет2" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_2))) }
            "Факульет3" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_3))) }
            "Факульет4" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_4))) }
            "Факульет5" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_5))) }
            else -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_6))) }
        }
        adapter = SubjectAdapter(list, requireContext())

        recyclerViewSubjects.adapter = adapter
        recyclerViewSubjects.layoutManager = LinearLayoutManager(activity)
        recyclerViewSubjects.setHasFixedSize(true)
    }

    private fun fillArray(array: Array<String>): List<FacultySubjects> {
        val listArray = ArrayList<FacultySubjects>()

        for (element in array) {
            val facultySubjects = FacultySubjects(element)
            listArray.add(facultySubjects)
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