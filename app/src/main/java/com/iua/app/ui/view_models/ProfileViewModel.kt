package com.iua.app.ui.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iua.app.mock.Profile
import com.iua.app.mock.ProfilePopularList
import com.iua.app.mock.profileMock
import com.iua.app.mock.profilePopularList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _profile = MutableLiveData<Profile>()
    val profile: MutableLiveData<Profile> = _profile

    private val _popularProjects = MutableStateFlow<List<ProfilePopularList>>(emptyList())
    val popularProjects: StateFlow<List<ProfilePopularList>> = _popularProjects

    init {
        loadMockData()
    }

    private fun loadMockData() {
        _popularProjects.value = profilePopularList
        _profile.value = profileMock
    }
}