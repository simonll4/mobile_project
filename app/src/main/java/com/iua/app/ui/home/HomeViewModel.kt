package com.iua.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _greeting = MutableLiveData<String>()
    val greeting: LiveData<String> = _greeting

    fun onGreeting() {
        println("hello")
    }
}