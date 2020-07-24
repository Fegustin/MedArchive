package com.example.medarchive.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medarchive.R
import com.example.medarchive.adapter.SubjectAdapter
import com.example.medarchive.pojo.FacultySubjects
import kotlinx.android.synthetic.main.fragment_subjects.*


class SubjectsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        recyclerViewSubjects.adapter = SubjectAdapter(list, requireContext())
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
}