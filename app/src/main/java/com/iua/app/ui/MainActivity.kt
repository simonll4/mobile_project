package com.iua.app.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.iua.app.data.datastore.isUserLoggedIn
import com.iua.app.data.datastore.setEventId
import com.iua.app.ui.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import com.iua.app.ui.theme.MyApplicationTheme
import com.iua.app.scheduler.WorkManagerInitializer
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("NotificationPermission", "Permiso concedido")
            Toast.makeText(this, "Permiso de notificaciones concedido", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("NotificationPermission", "Permiso denegado")
            Toast.makeText(this, "Permiso de notificaciones denegado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Permiso ya otorgado
                    Log.d("NotificationPermission", "Permiso ya otorgado")
                }

                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    // Explica por qué necesitas el permiso y vuelve a solicitarlo
                    Toast.makeText(
                        this,
                        "Se requiere el permiso para notificaciones.",
                        Toast.LENGTH_SHORT
                    ).show()
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }

                else -> {
                    // Solicita el permiso directamente
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }

    @Inject
    lateinit var workManagerInitializer: WorkManagerInitializer

    @Inject
    lateinit var workManagerConfigurator: WorkManagerInitializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verifica y solicita el permiso de notificaciones
        checkAndRequestNotificationPermission()

        // Configura el WorkManager
        workManagerConfigurator.setupPeriodicWorkIfNeeded()
        // Ejecuta un trabajo inmediato (pruebas)
        workManagerConfigurator.scheduleImmediateCheck()

        lifecycleScope.launch {
            val isUserLoggedIn = isUserLoggedIn(this@MainActivity)
            val eventId = intent?.getStringExtra("eventId")
            setEventId(this@MainActivity, eventId.toString())

            setContent {
                MyApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavigation(isUserLoggedIn = isUserLoggedIn, startEventId = eventId)
                    }
                }
            }
        }
    }
}
