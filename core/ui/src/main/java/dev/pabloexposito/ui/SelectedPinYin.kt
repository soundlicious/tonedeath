package dev.pabloexposito.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.pabloexposito.model.data.Pinyin

@Composable
fun SelectedPinYin(pinyin: Pinyin, modifier: Modifier = Modifier) {
    Box(modifier = modifier.aspectRatio(1f)) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.window),
            contentDescription = null
        )
        AutoSizeText(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .align(Alignment.Center),
            text = pinyin.toString(),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun SelectedPinYinPreview() {
    AppTheme {
        SelectedPinYin(pinyin = Pinyin("zh", "ang"))
    }
}