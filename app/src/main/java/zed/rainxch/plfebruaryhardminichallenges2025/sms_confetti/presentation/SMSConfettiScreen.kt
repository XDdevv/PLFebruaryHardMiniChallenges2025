package zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.presentation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.domain.SmsMessage

@Composable
fun SMSConfettiScreen(
    modifier: Modifier = Modifier
) {
    var showConfetti by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var smsList by remember { mutableStateOf<List<SmsMessage>>(emptyList()) }
    LaunchedEffect(Unit) {
        smsList = getSMSList()
    }
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
    }
}

fun getSMSList(context: Context): List<SmsMessage> {
    val smsList = mutableListOf<SmsMessage>()
    val uri = "content://sms/inbox".toUri()
    val projection = arrayOf("_id", "address", "body", "date")
    val cursor = context.contentResolver.query(uri, projection, null, null, "date DESC")

    cursor?.use {
        val idIndex = it.getColumnIndex(projection[0])
        val addressIndex = it.getColumnIndex(projection[1])
        val bodyIndex = it.getColumnIndex(projection[2])
        val dateIndex = it.getColumnIndex(projection[3])

        while (it.moveToNext()) {
            val from = it.getString(addressIndex).toIntOrNull() ?: 0
            val message = it.getString(bodyIndex)

            smsList.add(SmsMessage(from, message))
        }
    }
    return smsList
}