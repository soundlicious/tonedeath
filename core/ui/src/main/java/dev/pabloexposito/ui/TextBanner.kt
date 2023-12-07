package dev.pabloexposito.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.pabloexposito.designsystem.theme.material_color_deep_purple_700
import dev.pabloexposito.designsystem.theme.material_color_pink_700

@Composable
fun TextBanner(text: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth(0.80f)
                .align(Alignment.Center),
            maxLines = 4,
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = material_color_deep_purple_700
        )
        Image(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .aspectRatio(1f)
                .align(alignment = Alignment.TopStart),
            painter = painterResource(id = R.drawable.ic_corner_top_left),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                material_color_pink_700
            )
        )
        Image(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .aspectRatio(1f)
                .align(alignment = Alignment.BottomEnd),
            painter = painterResource(id = R.drawable.ic_corner_bottom_right),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                material_color_pink_700
            )
        )
    }
}

@Preview
@Composable
private fun TextBannerPreview() {
    AppTheme {
        TextBanner(
            text = "Listen to the difference between these tones",
            modifier = Modifier.fillMaxWidth()
        )
    }
}