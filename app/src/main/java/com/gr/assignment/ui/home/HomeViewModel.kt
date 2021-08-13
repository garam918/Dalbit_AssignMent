package com.gr.assignment.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gr.assignment.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application)
    private val lectures = repository.getAllLecture()
    private val classes = repository.getAllPublicClass()

    fun getInfo() = runBlocking {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getLectureInfo()
        }
    }
}