package zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.domain.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.domain.model.SmsMessage

class SmsReceiver : BroadcastReceiver() {

    companion object{
        var showAnimation by mutableStateOf<Boolean>(false)
            private set

        var list by mutableStateOf<List<SmsMessage>>(emptyList())
            private set

        const val TARGET_SMS_PHONE = "4444555551"
        const val TARGET_SMS_MESSAGE = "Congratulations! You've earned 500 points."
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            for (sms in messages) {
                val sender = sms.originatingAddress ?: ""
                val messageBody = sms.messageBody

                CoroutineScope(Dispatchers.IO).launch {
                    processSms(
                        SmsMessage(sender, messageBody)
                    )
                }
            }
        }
    }

    private suspend fun processSms(smsMessage: SmsMessage){
        list += smsMessage
        if (smsMessage.from == TARGET_SMS_PHONE && smsMessage.message == TARGET_SMS_MESSAGE) {
            showAnimation = true
            delay(4500)
            showAnimation = false
        }
    }

}