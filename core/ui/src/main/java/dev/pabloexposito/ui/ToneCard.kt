package dev.pabloexposito.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.designsystem.theme.material_color_deep_purple_700
import dev.pabloexposito.designsystem.theme.material_color_pink_700
import dev.pabloexposito.designsystem.theme.material_color_purple_700
import dev.pabloexposito.designsystem.theme.material_color_red_700

@Composable
fun ToneCard(
    modifier: Modifier = Modifier,
    tone: Tone,
    forceColor: Color? = null,
    onToneClick: (() -> Unit)?
) {
    val (icon, color) = when (tone) {
        Tone.TONE1 -> Pair(
            painterResource(id = R.drawable.tone_1),
            forceColor ?: material_color_red_700
        )

        Tone.TONE2 -> Pair(
            painterResource(id = R.drawable.tone_2),
            forceColor ?: material_color_pink_700
        )

        Tone.TONE3 -> Pair(
            painterResource(id = R.drawable.tone_3),
            forceColor ?: material_color_purple_700
        )

        Tone.TONE4 -> Pair(
            painterResource(id = R.drawable.tone_4),
            forceColor ?: material_color_deep_purple_700
        )
    }

    Card(
        modifier = modifier
            .aspectRatio(1.0f, matchHeightConstraintsFirst = true)
            .bouncingClickable(enabled = onToneClick != null) {
                onToneClick?.invoke()
            }
    ) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxSize()
                .background(color)
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(fraction = 0.5f),
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }
    }
}

@Preview
@Composable
fun ToneCardPreview(@PreviewParameter(TonePreviewParameterProvider::class) tone: Tone) {
    AppTheme {
        ToneCard(tone = tone) {}
    }
}

