package dev.pabloexposito.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.pabloexposito.designsystem.theme.material_color_deep_purple_500
import dev.pabloexposito.designsystem.theme.material_color_pink_700

@Composable
fun PracticeTopComponent(score: Int, modifier: Modifier = Modifier, onPlayClick: () -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Play",
            modifier = Modifier
                .fillMaxSize()
                .bouncingClickable {
                    onPlayClick()
                },
            colorFilter = ColorFilter.tint(material_color_deep_purple_500)
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxHeight(0.3f)
                .aspectRatio(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_corner_top_left),
                contentDescription = null,
                colorFilter = ColorFilter.tint(material_color_pink_700),
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxHeight(0.3f)
                .aspectRatio(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_corner_bottom_right),
                contentDescription = null,
                colorFilter = ColorFilter.tint(material_color_pink_700),
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            ScoreComponent(score = score)
        }
    }
}

@Preview
@Composable
private fun PracticeTopComponentPreview() {
    AppTheme {
        PracticeTopComponent(score = 1) {}
    }
}