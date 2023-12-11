package dev.pabloexposito.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.designsystem.theme.material_color_green_700
import dev.pabloexposito.designsystem.theme.material_color_red_700

@Composable
fun RowToneComparison(
    goodTone: Tone,
    badTone: Tone,
    modifier: Modifier = Modifier,
    onToneSelected: (tone: Tone) -> Unit
) {
    Row(modifier = modifier) {
        ToneCard(
            modifier = Modifier.weight(1f).padding(8.dp),
            tone = goodTone,
            forceColor = material_color_green_700
        ) {
            onToneSelected(goodTone)
        }
        ToneCard(
            modifier = Modifier.weight(1f).padding(8.dp),
            tone = badTone,
            forceColor = material_color_red_700
        ) {
            onToneSelected(badTone)
        }
    }
}

@Composable
fun ColumnToneComparison(
    goodTone: Tone,
    badTone: Tone,
    modifier: Modifier = Modifier,
    onToneSelected: (tone: Tone) -> Unit
) {
    Column(modifier = modifier) {
        ToneCard(
            modifier = Modifier.weight(1f).padding(8.dp),
            tone = goodTone,
            forceColor = material_color_green_700
        ) {
            onToneSelected(goodTone)
        }
        ToneCard(
            modifier = Modifier.weight(1f).padding(8.dp),
            tone = badTone,
            forceColor = material_color_red_700
        ) {
            onToneSelected(badTone)
        }
    }
}

@Preview
@Composable
private fun ColumnToneComparisonPreview() {
    AppTheme {
        ColumnToneComparison(
            modifier = Modifier
                .fillMaxWidth(),
            goodTone = Tone.TONE3,
            badTone = Tone.TONE4,
            onToneSelected = {})
    }
}

@Preview
@Composable
private fun RowToneComparisonPreview() {
    AppTheme {
        RowToneComparison(
            modifier = Modifier
                .fillMaxWidth(),
            goodTone = Tone.TONE3,
            badTone = Tone.TONE4,
            onToneSelected = {})
    }
}