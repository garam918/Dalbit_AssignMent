package com.gr.assignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gr.assignment.R
import com.gr.assignment.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    private lateinit var binding : FragmentWebViewBinding
    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_web_view,container,false)
        viewModel = ViewModelProvider(this.requireActivity()).get(HomeViewModel::class.java)

        init()

        return binding.root
    }

    private fun init() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val htmlData = arguments?.getString("html")
        binding.webView.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
            displayZoomControls = true
            setSupportMultipleWindows(true)
        }

        binding.webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }

        binding.webView.loadDataWithBaseURL(null,htmlData,"text/html; charset=utf-8","UTF-8", null)

    }

}