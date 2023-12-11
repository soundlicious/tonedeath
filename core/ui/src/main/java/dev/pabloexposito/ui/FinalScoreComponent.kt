package dev.pabloexposito.ui

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FinalScore(finalScore: Int, modifier: Modifier = Modifier) {
    val duration = 1000
    val transition = rememberInfiniteTransition(label = "")
    val translationY by transition.animateFloat(
        initialValue = 0f,
        targetValue = -30f,
        animationSpec = infiniteRepeatable(
            tween(duration, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse,
        ), label = ""
    )

    Box(modifier = modifier) {
        AutoSizeText(
            modifier = Modifier.align(alignment = Alignment.Center).fillMaxWidth(0.1f),
            text = "$finalScore"
        )
        Image(
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .fillMaxWidth(0.45f)
                .fillMaxHeight()
                .graphicsLayer {
                    this.translationY = translationY
                },
            contentScale = ContentScale.Inside,
            painter = painterResource(id = R.drawable.ghost_1),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth(0.1f),
            painter = painterResource(id = R.drawable.ic_coin),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .fillMaxWidth(0.45f)
                .fillMaxHeight()
                .graphicsLayer {
                    this.translationY = translationY
                },
            contentScale = ContentScale.Inside,
            painter = painterResource(id = R.drawable.ghost_2),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun FinalScorePreview() {
    AppTheme {
        FinalScore(
            finalScore = 0, modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
    }
}