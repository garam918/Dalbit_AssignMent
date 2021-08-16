package com.gr.assignment.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gr.assignment.R

class PeopleFragment : Fragment() {

    private lateinit var peopleViewModel: PeopleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        peopleViewModel = ViewModelProvider(this.requireActivity()).get(PeopleViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_people, container, false)

        return root
    }
}