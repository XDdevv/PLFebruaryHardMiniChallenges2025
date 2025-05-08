package zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.domain.model.SmsMessage
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.presentation.components.ConfettiAnimation
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.presentation.components.SmsItem

@Composable
fun SMSConfettiScreen(
    showAnimation: Boolean,
    smsList: List<SmsMessage>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xffF6F6F6))
                .padding(innerPadding)
        ) {
            items(smsList) { sms ->
                SmsItem(
                    smsMessage = sms
                )
            }
        }
        if (showAnimation) {
            ConfettiAnimation(
                confettiCount = 100
            )
        }
    }
}