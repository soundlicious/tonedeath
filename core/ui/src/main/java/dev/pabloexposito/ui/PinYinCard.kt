package dev.pabloexposito.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.pabloexposito.designsystem.theme.pinyinColorList

@Composable
fun PinYinCard(
    init: String,
    index: Int,
    fixColor: Color? = null,
    onClick: (element: String) -> Unit
) {
    Card(modifier = Modifier.aspectRatio(1f).bouncingClickable { onClick(init) }) {
        AutoSizeText(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            maxLines = 1,
            text = init.uppercase(),
            textAlign = TextAlign.Center,
            color = fixColor ?: pinyinColorList.elementAt(index % pinyinColorList.size)
        )
    }
}

@Preview
@Composable
fun PinYinCardPreview() {
    AppTheme {
        PinYinCard("ang", 0) {}
    }
}