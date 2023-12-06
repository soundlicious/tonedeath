package dev.pabloexposito.learning

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.pabloexposito.common.FINAL_PARAMETER
import dev.pabloexposito.common.INITIAL_PARAMETER
import dev.pabloexposito.model.data.Pinyin
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.data.repository.AppPinYinRepository
import dev.pabloexposito.navigation.BaseScreen
import dev.pabloexposito.navigation.ScreenParams
import dev.pabloexposito.ui.ToneCardsDisplay
import dev.pabloexposito.ui.DevicePreviews
import dev.pabloexposito.ui.SelectedPinYin
import dev.pabloexposito.ui.squareShapedOnMinSize

internal data class ListeningScreenParameters(
    val init: String,
    val final: String,
) : ScreenParams {
    override val paramsList: Map<String, String>
        get() = mapOf(
            ListeningScreen.initParamName to init,
            ListeningScreen.finalParamName to final
        )
}

data object ListeningScreen : BaseScreen.Screen {
    const val initParamName = INITIAL_PARAMETER
    const val finalParamName = FINAL_PARAMETER
    override val orderedParameters: List<String>
        get() = listOf(initParamName, finalParamName)
    override val root: String
        get() = "listening"
}

internal fun NavGraphBuilder.listeningDestination() {
    composable(
        route = ListeningScreen.path,
        arguments = listOf(
            navArgument(ListeningScreen.initParamName) {
                type = NavType.StringType
            },
            navArgument(ListeningScreen.finalParamName) {
                type = NavType.StringType
            }
        ),
    ) {
        ListeningRoute()
    }
}

@Composable
internal fun ListeningRoute(
    viewModel: ListeningViewModel = hiltViewModel(),
) {
    val pinyin = viewModel.selectedPinYin
    val enableButtons by viewModel.enableButtons.observeAsState()
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    PinYinListening(pinyin, enableButtons, isLandscape) { tone ->
        viewModel.onToneSelected(tone)
    }
}

@Composable
private fun PinYinListening(
    pinyin: Pinyin,
    enableButtons: Boolean?,
    isLandscape: Boolean,
    onToneClick: (tone: Tone) -> Unit
) {
    if (isLandscape) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SelectedPinYin(
                modifier = Modifier
                    .squareShapedOnMinSize()
                    .weight(1f),
                pinyin = pinyin
            )
            ToneCardsDisplay(
                modifier = Modifier
                    .squareShapedOnMinSize()
                    .weight(1f),
                displayButtons = true,
                onToneClick = onToneClick.takeIf { enableButtons == true })
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SelectedPinYin(
                modifier = Modifier
                    .squareShapedOnMinSize()
                    .weight(1f),
                pinyin = pinyin
            )
            ToneCardsDisplay(
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f),
                displayButtons = true,
                onToneClick = onToneClick.takeIf { enableButtons == true })
        }
    }
}

@DevicePreviews
@Composable
fun ListeningViewPreview() {
    AppTheme {
        PinYinListening(
            AppPinYinRepository().getRandomPinyin(),
            true,
            LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        ) {}
    }
}