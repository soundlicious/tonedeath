package dev.pabloexposito.practice

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.pabloexposito.common.FINAL_PARAMETER
import dev.pabloexposito.common.GOOD_TONE
import dev.pabloexposito.common.INITIAL_PARAMETER
import dev.pabloexposito.common.SCORE
import dev.pabloexposito.common.SCREEN_NAME_PARAMETER
import dev.pabloexposito.common.SELECTED_TONE
import dev.pabloexposito.common.VOICE_GENDER
import dev.pabloexposito.common.VOICE_NUMBER
import dev.pabloexposito.core.data.VoiceGender
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.model.data.VoiceNumber
import dev.pabloexposito.navigation.BaseScreen
import dev.pabloexposito.navigation.ScreenParams
import dev.pabloexposito.ui.ColumnToneComparison
import dev.pabloexposito.ui.FinalScore
import dev.pabloexposito.ui.RowToneComparison
import dev.pabloexposito.ui.TextBanner
import dev.pabloexposito.ui.DevicePreviews

internal data class GameOverScreenParameters(
    val init: String,
    val final: String,
    val score: Int,
    val goodTone: Tone,
    val badTone: Tone,
    val voiceGender: VoiceGender,
    val voiceNumber: VoiceNumber
) : ScreenParams {
    override val paramsList: Map<String, String>
        get() = mapOf(
            GameOverScreen.initParamName to init,
            GameOverScreen.finalParamName to final,
            GameOverScreen.scoreParamName to score.toString(),
            GameOverScreen.goodToneParamName to goodTone.toString(),
            GameOverScreen.badToneParamName to badTone.toString(),
            GameOverScreen.voiceGenderParamName to voiceGender.toString(),
            GameOverScreen.voiceNumberParamName to voiceNumber.toString()
        )
}

data object GameOverScreen : BaseScreen.Screen {
    const val initParamName = INITIAL_PARAMETER
    const val finalParamName = FINAL_PARAMETER
    const val scoreParamName = SCORE
    const val goodToneParamName = GOOD_TONE
    const val badToneParamName = SELECTED_TONE
    const val voiceGenderParamName = VOICE_GENDER
    const val voiceNumberParamName = VOICE_NUMBER
    override val orderedParameters: List<String>
        get() = listOf(
            initParamName,
            finalParamName,
            goodToneParamName,
            badToneParamName,
            voiceGenderParamName,
            voiceNumberParamName,
            scoreParamName,
            )
    override val root: String
        get() = "game_over"
}


internal fun NavGraphBuilder.gameOverDestination() {
    composable(
        route = GameOverScreen.path,
        arguments = listOf(
            navArgument(SCREEN_NAME_PARAMETER) {
                type = NavType.StringType
            },
            navArgument(GameOverScreen.initParamName) {
                type = NavType.StringType
            },
            navArgument(GameOverScreen.finalParamName) {
                type = NavType.StringType
            },
            navArgument(GameOverScreen.scoreParamName) {
                type = NavType.StringType
            },
            navArgument(GameOverScreen.goodToneParamName) {
                type = NavType.StringType
            },
            navArgument(GameOverScreen.badToneParamName) {
                type = NavType.StringType
            },
            navArgument(GameOverScreen.voiceGenderParamName) {
                type = NavType.StringType
            },
            navArgument(GameOverScreen.voiceNumberParamName) {
                type = NavType.StringType
            },
        ),
    ) { GameOverRoute() }
}

@Composable
private fun GameOverRoute(
    viewModel: GameOverViewModel = hiltViewModel()
) {
    GameOver(
        modifier = Modifier.fillMaxSize(),
        isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE,
        score = viewModel.finalScore,
        goodTone = viewModel.goodTone,
        badTone = viewModel.badTone,
        onToneSelected = viewModel::onToneSelected,
    )
}

@Composable
private fun GameOver(
    isLandscape: Boolean,
    score: Int,
    goodTone: Tone,
    badTone: Tone,
    modifier: Modifier = Modifier,
    onToneSelected: (tone: Tone) -> Unit
) {
    if (isLandscape) {
        Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .weight(0.3f)
            ) {
                FinalScore(
                    modifier = Modifier.fillMaxSize(),
                    finalScore = score
                )
            }
            TextBanner(
                text = stringResource(R.string.game_over_listen_tone_difference),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.3f)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                ColumnToneComparison(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Center),
                    goodTone = goodTone, badTone = badTone, onToneSelected = onToneSelected
                )
            }
        }
    } else {
        Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly) {
            FinalScore(
                finalScore = score, modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            )
            TextBanner(
                text = stringResource(R.string.game_over_listen_tone_difference),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                RowToneComparison(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(alignment = Center),
                    goodTone = goodTone, badTone = badTone, onToneSelected = onToneSelected
                )
            }

        }
    }
}

@DevicePreviews
@Composable
fun GameOverPreview() {
    AppTheme {
        GameOver(
            modifier = Modifier.fillMaxSize(),
            isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE,
            score = 0,
            goodTone = Tone.TONE4,
            badTone = Tone.TONE2,
            onToneSelected = {},
        )
    }
}