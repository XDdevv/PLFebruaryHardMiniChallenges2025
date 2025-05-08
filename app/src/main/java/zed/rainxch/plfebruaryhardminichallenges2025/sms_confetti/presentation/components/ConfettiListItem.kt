package zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.domain.model.ConfettiItem
import zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.domain.model.ConfettiShape

@Composable
fun FallingConfetti(item: ConfettiItem) {
    var animationStarted by remember { mutableStateOf(false) }

    val yOffset = remember { Animatable(item.initialYOffset) }

    LaunchedEffect(key1 = item) {
        animationStarted = true
        yOffset.animateTo(
            targetValue = 1200f,
            animationSpec = tween(
                durationMillis = item.animationDuration,
                easing = LinearEasing
            )
        )
    }

    val rotation by rememberInfiniteTransition(label = "rotation").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (2000..4000).random(),
                easing = LinearEasing
            )
        ),
        label = "confetti-rotation"
    )

    val wiggleX by rememberInfiniteTransition(label = "wiggle").animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "confetti-wiggle"
    )

    val shape = when (item.shape) {
        ConfettiShape.CIRCLE -> CircleShape
        ConfettiShape.TRIANGLE -> CutCornerShape(100f)
        ConfettiShape.SQUARE -> CutCornerShape(0f)
    }

    if (animationStarted) {
        Box(
            modifier = Modifier
                .absoluteOffset(x = item.xOffset.dp)
                .size(item.size.dp)
                .clip(shape)
                .background(item.color)
                .graphicsLayer {
                    translationY = yOffset.value
                    translationX = wiggleX
                    rotationZ = rotation
                }
        )
    }
}