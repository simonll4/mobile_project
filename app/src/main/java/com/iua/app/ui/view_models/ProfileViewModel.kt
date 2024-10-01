package com.iua.app.ui.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iua.app.mock.FeatureList
import com.iua.app.mock.ImageTextList
import com.iua.app.mock.Profile
import com.iua.app.mock.ProfilePopularList
import com.iua.app.mock.imagesTextList
import com.iua.app.mock.moreOptionsList
import com.iua.app.mock.profilePopularList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

//class ProfileViewModel @Inject constructor() : ViewModel() {
//    private val _profile = MutableLiveData<Profile>()
//    val profile: MutableLiveData<Profile> = _profile
//
//    init {
//        loadMockProfile()
//    }
//
//    private fun loadMockProfile() {
//        viewModelScope.launch {
//            _profile.value = profileMock
//        }
//    }
//}

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _profile = MutableLiveData<Profile>()
    val profile: MutableLiveData<Profile> = _profile

    private val _moreOptions = MutableStateFlow<List<FeatureList>>(emptyList())
    val moreOptions: StateFlow<List<FeatureList>> = _moreOptions

    private val _popularProjects = MutableStateFlow<List<ProfilePopularList>>(emptyList())
    val popularProjects: StateFlow<List<ProfilePopularList>> = _popularProjects

    private val _imageTextList = MutableStateFlow<List<ImageTextList>>(emptyList())
    val imageTextList: StateFlow<List<ImageTextList>> = _imageTextList

    init {
        loadMockData()
    }

    private fun loadMockData() {
        // Set the mock data
        _moreOptions.value = moreOptionsList
        _popularProjects.value = profilePopularList
        _imageTextList.value = imagesTextList
    }
}