package zed.rainxch.plfebruaryhardminichallenges2025

import android.Manifest
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.domain.receiver.SmsReceiver
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.presentation.SMSConfettiScreen
import zed.rainxch.plfebruaryhardminichallenges2025.ui.theme.PLFebruaryHardMiniChallenges2025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showScreen by remember { mutableStateOf(false) }
            var showAnimation = SmsReceiver.showAnimation
            var smsList = SmsReceiver.list

            RequestPermission {
                showScreen = true
            }

            PLFebruaryHardMiniChallenges2025Theme {
                if (showScreen) {
                    SMSConfettiScreen(
                        showAnimation,
                        smsList
                    )
                }
            }
        }
    }

    @Composable
    private fun RequestPermission(onPermissionGranted: () -> Unit) {
        val smsPermission = arrayOf(
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
        )
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                if (permissions.all { it.value }) {
                    onPermissionGranted()
                } else {
                    Toast.makeText(this, "Not granted", Toast.LENGTH_SHORT).show()
                }
            }

        LaunchedEffect(Unit) {
            smsPermission.forEach { permission ->
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    launcher.launch(smsPermission)
                } else {
                    onPermissionGranted()
                }
            }
        }

    }
}