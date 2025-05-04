package zed.rainxch.plfebruaryhardminichallenges2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.presentation.SMSConfettiScreen
import zed.rainxch.plfebruaryhardminichallenges2025.ui.theme.PLFebruaryHardMiniChallenges2025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PLFebruaryHardMiniChallenges2025Theme {
                SMSConfettiScreen()
            }
        }
    }
}
