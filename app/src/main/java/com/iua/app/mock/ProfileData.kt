package com.iua.app.mock

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Share

val profileMock = Profile(
    id = 1,
    name = "John",
    lastName = "Doe",
    email = "john@gmail.com",
    phone = "+54351584712",
    avatar = "https://imgs.search.brave.com/y28ot5NphtrGnHdSWJlMnv-kIELrZwKoxIDrEmhjoDQ/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pLnBp/bmltZy5jb20vb3Jp/Z2luYWxzLzJmLzg2/LzZmLzJmODY2ZmUy/MGNjZTE3YTVlNjhi/OTcyODVkNWI2NTk5/LmpwZw"
)

val moreOptionsList = listOf(
    FeatureList("Edit Profile", Icons.Default.Edit, ""),
    FeatureList("Manage Account", Icons.Default.AccountBox, ""),
    FeatureList("Privacy Policy", Icons.Default.Policy, ""),
    FeatureList("About", Icons.Default.Info, ""),
    FeatureList("Help & Feedback", Icons.Default.Help, ""),
    FeatureList("Share 'Damahe Code'", Icons.Default.Share, ""),
)

val profilePopularList = listOf(
    ProfilePopularList(
        "Jetpack-Compose-UI",
        "A Collection on all Jetpack compose UI Layouts and Demo screens to see it's potential",
        "25",
        "Kotlin"
    ),
    ProfilePopularList(
        "Leaf-Explorer",
        "File Manager, File Sharing & Music Player App for Android",
        "9",
        "Kotlin"
    ),
    ProfilePopularList(
        "DayNight-Theme",
        "A Material Design-based Theme Management System for Android Jetpack Compose.",
        "45",
        "Kotlin"
    )
)

val imagesTextList = listOf(
    ImageTextList(Icons.Default.MyLocation, "Bharat/India"),
    ImageTextList(Icons.Default.Email, "damahecode@gmail.com"),
    ImageTextList(Icons.Default.AccountBox, "100 followers")
)