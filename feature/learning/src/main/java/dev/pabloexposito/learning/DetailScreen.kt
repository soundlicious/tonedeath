package dev.pabloexposito.learning

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.pabloexposito.common.INITIAL_PARAMETER
import dev.pabloexposito.data.repository.AppPinYinRepository
import dev.pabloexposito.ui.PinYinCard
import dev.pabloexposito.designsystem.theme.material_color_light_blue_700
import dev.pabloexposito.navigation.BaseScreen.Screen
import dev.pabloexposito.navigation.ScreenParams
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

internal data class LearningDetailsScreenParameters(
    val init: String,
) : ScreenParams {
    override val paramsList: Map<String, String>
        get() = mapOf(INITIAL_PARAMETER to init)
}

data object LearningDetailsScreen : Screen {
    const val initParamName = INITIAL_PARAMETER
    override val orderedParameters: List<String>
        get() = listOf(initParamName)
    override val root: String
        get() = "learning_details"
}


internal fun NavGraphBuilder.learningDetailsDestination() {
    composable(
        route = LearningDetailsScreen.path,
        arguments = listOf(
            navArgument(LearningDetailsScreen.initParamName) {
                type = NavType.StringType
            }
        ),
    ) {
        LearningDetailsRoute()
    }
}

@Composable
internal fun LearningDetailsRoute(
    viewModel: DetailViewModel = hiltViewModel()
) {
    PinYinDetailsLearningPane(
        modifier = Modifier.fillMaxSize(),
        finals = viewModel.details.toImmutableList()
    ) { final ->
        viewModel.onFinalSelected(final)
    }
}

@Composable
internal fun PinYinDetailsLearningPane(
    finals: ImmutableList<String>,
    modifier: Modifier = Modifier,
    onFinalSelected: (final: String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(finals) { index, final ->
            PinYinCard(init = final, index = index, fixColor = material_color_light_blue_700) {
                onFinalSelected(final)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDetailScreen() {
    AppTheme {
        PinYinDetailsLearningPane(
            modifier = Modifier.fillMaxSize(),
            finals = AppPinYinRepository().getFinalsByInitials("zh").toImmutableList()
        ) {}
    }
}