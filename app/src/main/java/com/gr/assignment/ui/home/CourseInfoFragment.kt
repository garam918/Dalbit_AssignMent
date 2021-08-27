package com.gr.assignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gr.assignment.R
import com.gr.assignment.databinding.FragmentCourseInfoBinding

class CourseInfoFragment : Fragment() {

    private lateinit var binding : FragmentCourseInfoBinding
    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_course_info,container,false)

        init()

        return binding.root
    }

    private fun init() {
        viewModel = ViewModelProvider(this.requireActivity()).get(HomeViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getCourseContent(this.requireActivity().supportFragmentManager,viewModel.currentSelectedCourseId.value!!.toInt())

    }

}