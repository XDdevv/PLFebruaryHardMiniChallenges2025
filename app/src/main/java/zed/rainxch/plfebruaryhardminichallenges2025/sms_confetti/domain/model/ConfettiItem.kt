package zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.domain.model

import androidx.compose.ui.graphics.Color

data class ConfettiItem(
    val color: Color,
    val size: Int,
    val shape: ConfettiShape,
    val initialYOffset: Float = (-100f),
    val xOffset: Float = 0f,
    val animationDuration: Int = 5000
)