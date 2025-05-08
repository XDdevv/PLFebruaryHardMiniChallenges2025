package zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.domain.model.SmsMessage

@Composable
fun SmsItem(
    smsMessage: SmsMessage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        Text(smsMessage.from.toString(), fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text(smsMessage.message, fontWeight = FontWeight.Normal)
    }
}