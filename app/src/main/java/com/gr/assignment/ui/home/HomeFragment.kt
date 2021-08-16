package com.gr.assignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        binding.lifecycleOwner = this
        binding.homeViewModel = homeViewModel

        init()

        return binding.root
    }

    // 강의 데이터가 없는 경우에만 요청하도록 했습니다.
    private fun init() {
        if(homeViewModel.lectureData.isEmpty() || homeViewModel.publicClassData.isEmpty()) homeViewModel.getLectureInfo()
    }
}