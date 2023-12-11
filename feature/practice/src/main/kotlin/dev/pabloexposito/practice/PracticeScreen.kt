package dev.pabloexposito.practice

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.navigation.BaseScreen
//import dev.pabloexposito.navigation.PracticeScreen
import dev.pabloexposito.ui.PracticeTopComponent
import dev.pabloexposito.ui.ToneCardsDisplay
import dev.pabloexposito.ui.ToneCardsLayout
import dev.pabloexposito.ui.WindowWidthSizeClassPreview
import dev.pabloexposito.ui.WindowWidthSizePreviewParameterProvider
import dev.pabloexposito.ui.squareShapedOnMinSize

data object PracticeScreen : BaseScreen.Screen {
    override val root: String
        get() = "practice"
}


internal fun NavGraphBuilder.practiceDestination() {
    composable(
        route = PracticeScreen.path,
        arguments = listOf(),
    ) {
        PracticeRoute()
    }
}

@Composable
private fun PracticeRoute(
    viewModel: PracticeViewModel = hiltViewModel()
) {
    val currentScore by viewModel.score.observeAsState(0)
    val displayButton by viewModel.displayButton.observeAsState(initial = false)

    PracticeView(
        configuration = LocalConfiguration.current,
        score = currentScore,
        displayToneButtons = displayButton,
        onPlayClick = { viewModel.onPlayEvent() }
    ) { tone -> viewModel.onToneSelected(tone) }
}

@Composable
private fun PracticeView(
    configuration: Configuration,
    score: Int,
    displayToneButtons: Boolean,
    onPlayClick: () -> Unit,
    onToneSelected: (tone: Tone) -> Unit
) {
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val toneCardLayout = ToneCardsLayout.GRID


    when {
        isLandscape -> RowLayout(
            score = score,
            onPlayClick = onPlayClick,
            displayToneButtons = displayToneButtons,
            onToneSelected = onToneSelected,
            toneCardsLayout = toneCardLayout
        )

        else -> ColumnLayout(
            score = score,
            onPlayClick = onPlayClick,
            displayToneButtons = displayToneButtons,
            onToneSelected = onToneSelected,
            toneCardsLayout = toneCardLayout
        )
    }
}

@Composable
private fun RowLayout(
    score: Int,
    onPlayClick: () -> Unit,
    displayToneButtons: Boolean,
    onToneSelected: (tone: Tone) -> Unit,
    toneCardsLayout: ToneCardsLayout,
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PracticeTopComponent(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            score = score,
            onPlayClick = onPlayClick
        )
        ToneCardsDisplay(
            modifier = Modifier
                .squareShapedOnMinSize()
                .weight(1f),
            displayButtons = displayToneButtons,
            onToneClick = onToneSelected,
            toneCardsLayout = toneCardsLayout
        )
    }
}


@Composable
private fun ColumnLayout(
    score: Int,
    onPlayClick: () -> Unit,
    displayToneButtons: Boolean,
    onToneSelected: (tone: Tone) -> Unit,
    toneCardsLayout: ToneCardsLayout,
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        PracticeTopComponent(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            score = score,
            onPlayClick = onPlayClick
        )
        ToneCardsDisplay(
            modifier = Modifier
                .apply {
                    if (toneCardsLayout == ToneCardsLayout.GRID) {
                        aspectRatio(1f)
                    }
                }.weight(1f),
            displayButtons = displayToneButtons,
            onToneClick = onToneSelected,
            toneCardsLayout = toneCardsLayout
        )
    }
}

@Preview(
    name = "phone",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "light"
)
@Preview(
    name = "landscape",
    device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "light"
)
@Preview(
    name = "landscape-small-ratio",
    device = "spec:shape=Normal,width=640,height=200,unit=dp,dpi=480",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "light"
)
@Composable
private fun PracticeScreenPreview(@PreviewParameter(WindowWidthSizePreviewParameterProvider::class) widthSizeClassPreview: WindowWidthSizeClassPreview) {
    AppTheme {
        PracticeView(
            configuration = LocalConfiguration.current,
            score = 1,
            displayToneButtons = true,
            onPlayClick = { }
        ) { }
    }
}