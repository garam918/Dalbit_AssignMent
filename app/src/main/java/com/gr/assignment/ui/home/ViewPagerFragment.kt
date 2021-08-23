package com.gr.assignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.gr.assignment.R
import com.gr.assignment.databinding.FragmentViewPagerBinding
import com.gr.assignment.util.PagerAdapter

class ViewPagerFragment : Fragment() {

    private lateinit var viewModel : HomeViewModel
    private lateinit var binding : FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager,container,false)
        viewModel = ViewModelProvider(this.requireActivity()).get(HomeViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        init()

        viewModel.currentSelectedCourseId.value = arguments?.get("courseId").toString().toInt()
        viewModel.currentSelectedCourseName.value = arguments?.get("courseName").toString()

        return binding.root
    }

    private fun init() {

        val pagerAdapter = PagerAdapter(this)
        pagerAdapter.addFragment(CourseInfoFragment())
        pagerAdapter.addFragment(MenuFragment())

        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

        })

        TabLayoutMediator(binding.tapLayout,binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "강좌정보"
                1 -> "메뉴"
                else -> ""
            }
        }.attach()
    }
}