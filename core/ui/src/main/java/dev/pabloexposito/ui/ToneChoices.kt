package dev.pabloexposito.ui

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import dev.pabloexposito.model.data.Tone

@Composable
fun ToneCardsDisplay(
    displayButtons: Boolean,
    modifier: Modifier = Modifier,
    toneCardsLayout: ToneCardsLayout = ToneCardsLayout.GRID,
    onToneClick: ((tone: Tone) -> Unit)? = null
) {
    Box(modifier) {
        val tones = Tone.values().asList()
        val animationDelayPerButton = 175
        var animationEndCount by remember { mutableIntStateOf(0) }
        ConstraintLayout(
            modifier = if (toneCardsLayout == ToneCardsLayout.GRID) {
                Modifier
                    .aspectRatio(1f)
                    .fillSmallestDimension()
            } else {
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            }
                .padding(8.dp)
                .align(alignment = Alignment.Center),
            constraintSet = ConstraintSet {
                val toneRef = tones.map { this.createRefFor(it) }

                val grid = createGrid(
                    *toneRef.toTypedArray(),
                    rows = toneCardsLayout.rows,
                    columns = toneCardsLayout.columns,
                    verticalGap = 8.dp,
                    horizontalGap = 8.dp
                )

                constrain(grid) {
                    width = Dimension.matchParent
                    height = Dimension.matchParent
                }

                toneRef.forEach { ref ->
                    constrain(ref) {
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                }
            },
        ) {
            tones.forEachIndexed { index, tone ->
                val alphaAnimation by animateFloatAsState(
                    targetValue = if (displayButtons) 1f else 0f,
                    animationSpec = tween(
                        durationMillis = if (displayButtons) 300 else 0,
                        delayMillis = if (displayButtons) index * animationDelayPerButton else 0,
                        easing = LinearEasing,
                    ),
                    finishedListener = { animationEndCount++ },
                    label = "Button Fade In Animation",
                )
                ToneCard(
                    tone = tone,
                    modifier = Modifier
                        .alpha(alphaAnimation)
                        .padding(4.dp)
                        .layoutId(tone),
                    onToneClick = onToneClick?.let { { it.invoke(tone) } }
                        ?.takeIf { alphaAnimation == 1f }
                )
            }
        }
    }
}

@Preview(
    name = "landscape-small-ratio",
    device = "spec:shape=Normal,width=640,height=200,unit=dp,dpi=480",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "light"
)
@Composable
private fun ToneChoicePreview(@PreviewParameter(ToneCardsLayoutPreviewProvider::class) toneCardsLayout: ToneCardsLayout) {
    AppTheme {
        ToneCardsDisplay(
            modifier = Modifier.fillMaxSize(),
            displayButtons = true,
            onToneClick = {},
            toneCardsLayout = toneCardsLayout
        )
    }
}