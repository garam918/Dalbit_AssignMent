package com.gr.assignment.util

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragment : Fragment): FragmentStateAdapter(fragment){
    var fragments = ArrayList<Fragment>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
//        notifyItemInserted(fragments.size-1)
    }

}