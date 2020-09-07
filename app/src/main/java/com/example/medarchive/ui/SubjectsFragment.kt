package com.example.medarchive.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medarchive.R
import com.example.medarchive.adapter.SubjectAdapter
import com.example.medarchive.pojo.FacultySubjects
import kotlinx.android.synthetic.main.fragment_subjects.*


class SubjectsFragment : Fragment() {

    private var subjectAdapter: SubjectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("test", "subject")
        return inflater.inflate(R.layout.fragment_subjects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ArrayList<FacultySubjects>()

        // По разному заполняет List для адаптера в зависимости от пришедших данных (Потом нужно будет изменить код)
        val args = arguments?.let { SubjectsFragmentArgs.fromBundle(it) }

        when(args?.subject){
            "Факульет1" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_cource_1))) }
            "Факульет2" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_2))) }
            "Факульет3" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_3))) }
            "Факульет4" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_4))) }
            "Факульет5" -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_subjects_5))) }
            else -> { list.addAll(fillArray(resources.getStringArray(R.array.faculty_cource_1))) }
        }

        subjectAdapter = SubjectAdapter(list.sortedBy { it.items }, requireContext())
        // ------------------ //

        recyclerViewSubjects.apply {
            adapter = subjectAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
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

        // Поиск
        val menuItem = menu.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                subjectAdapter?.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                subjectAdapter?.filter?.filter(newText)
                return false
            }

        })
        // ---------- //
    }

}