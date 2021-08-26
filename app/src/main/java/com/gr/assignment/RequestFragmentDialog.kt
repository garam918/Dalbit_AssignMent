package com.gr.assignment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.gr.assignment.databinding.FragmentRequestDialogBinding

class RequestFragmentDialog : DialogFragment() {

    private lateinit var binding : FragmentRequestDialogBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_request_dialog,container,false)
        viewModel = ViewModelProvider(this.requireActivity()).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.dismissButton.setOnClickListener {
            this.dismiss()
        }

        binding.reLoginButton.setOnClickListener {
            val intent = Intent(this.context,MainActivity::class.java)
            this.requireActivity().startActivity(intent)
            this.dismiss()
        }

        return binding.root

    }

}