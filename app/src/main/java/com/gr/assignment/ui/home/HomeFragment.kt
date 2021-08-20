package com.gr.assignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.gr.assignment.R
import com.gr.assignment.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this.requireActivity()).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        init()

        return binding.root
    }

    private fun init() {
        binding.lifecycleOwner = this
        binding.homeViewModel = homeViewModel

        homeViewModel.getCourseList()
    }
}