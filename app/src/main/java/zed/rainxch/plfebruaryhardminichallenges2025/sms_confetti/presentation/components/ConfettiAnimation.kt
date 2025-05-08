package zed.rainxch.plfebruaryhardminichallenges2025.sms_confetti.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ConfettiAnimation(
    confettiCount: Int,
    modifier: Modifier = Modifier
) {
    val screenWidth =
        with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val screenHeight =
        with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx() }

    val confettiList = remember {
        List(confettiCount) {
            ConfettiItem(
                color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
                initialX = Random.nextFloat() * screenWidth,
                size = (16..28).random().toFloat(),
                initialY = (-screenHeight.toInt()..-100).random().toFloat(),
                shape = Shape.entries.toTypedArray().random()
            )
        }
    }

    LaunchedEffect(Unit) {
        confettiList.forEach { confetti ->
            launch {
                val endY = screenHeight + (32..300).random().toFloat()
                confetti.y.animateTo(
                    targetValue = endY,
                    animationSpec = tween(
                        durationMillis = 3000,
                        easing = LinearEasing
                    )
                )
            }
            launch {
                val newX = (-200..screenWidth.toInt()+200).random().toFloat()
                val driftDuration = (6000)
                confetti.x.animateTo(
                    targetValue = newX,
                    animationSpec = tween(
                        durationMillis = driftDuration,
                        easing = LinearEasing
                    )
                )
            }
        }
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
//            .background(Color.White)
    ) {
        confettiList.forEach { confetti ->
            when (confetti.shape) {
                Shape.CIRCLE -> {
                    drawCircle(
                        confetti.color,
                        radius = confetti.size,
                        center = Offset(confetti.x.value, y = confetti.y.value)
                    )
                    Path
                }

                Shape.SQUARE -> {
                    drawRect(
                        confetti.color,
                        size = Size(confetti.size + 20f, confetti.size + 20f),
                        topLeft = Offset(confetti.x.value, confetti.y.value)
                    )
                }

                Shape.TRIANGLE -> {
                    val trianglePath = Path()
                    val half = confetti.size / 2f
                    trianglePath.moveTo(confetti.x.value, confetti.y.value)
                    trianglePath.lineTo(
                        confetti.x.value - half,
                        confetti.y.value + confetti.size + 20
                    )
                    trianglePath.lineTo(
                        confetti.x.value + half,
                        confetti.y.value + confetti.size + 20f
                    )
                    trianglePath.close()
                    drawPath(
                        path = trianglePath,
                        color = confetti.color
                    )
                }
            }
        }
    }
}

@Stable
data class ConfettiItem(
    val color: Color,
    val size: Float,
    val shape: Shape,
    val initialX: Float,
    private val initialY: Float
) {
    val x = Animatable(initialX)
    val y = Animatable(initialY)
}

enum class Shape {
    CIRCLE, SQUARE, TRIANGLE
}

@Preview
@Composable
fun ConfettiAnimationPreview(modifier: Modifier = Modifier) {
    ConfettiAnimation(100)
}
