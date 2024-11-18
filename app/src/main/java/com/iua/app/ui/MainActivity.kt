package com.iua.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.iua.app.ui.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import com.iua.app.ui.theme.MyApplicationTheme
import com.iua.app.work.WorkManagerInitializer
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var workManagerInitializer: WorkManagerInitializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }

            workManagerInitializer.scheduleImmediateCheck()
        }
    }


}