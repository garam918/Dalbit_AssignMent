package com.gr.assignment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gr.assignment.R
import com.gr.assignment.databinding.FragmentContentBinding

class ContentFragment : Fragment() {

    private lateinit var binding : FragmentContentBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_content,container,false)

        init()

        return binding.root
    }

    private fun init() {
        viewModel = ViewModelProvider(this.requireActivity()).get(HomeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.currentSelectedContentsId.value = arguments?.get("contentsId").toString().toInt()
        viewModel.currentSelectedContentsName.value = arguments?.getString("contentsName")

        viewModel.getBoard(this.requireActivity().supportFragmentManager,viewModel.currentSelectedContentsId.value!!.toInt())

    }

}