package dev.pabloexposito.learning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.pabloexposito.navigation.BaseScreen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data object LearningScreen : BaseScreen.Screen {
    override val root: String
        get() = "learning"
}


internal fun NavGraphBuilder.learningDestination(
    value: WindowWidthSizeClass,
) {
    composable(
        route = LearningScreen.path,
        arguments = listOf(),
    ) {
        LearningRoute(value)
    }
}

@Composable
private fun LearningRoute(
    value: WindowWidthSizeClass,
    learningViewModel: LearningViewModel = hiltViewModel(),
) {
    var details: String? by rememberSaveable {
        mutableStateOf(null)
    }
    val finals: ImmutableList<String> by remember {
        derivedStateOf {
            (details?.let { detail ->
                learningViewModel.getFinals(detail)
            } ?: emptyList()).toImmutableList()
        }
    }
    val distinctInitials: ImmutableList<String> = learningViewModel.getDistinctInitials().toImmutableList()

    when (value) {
        WindowWidthSizeClass.Medium,
        WindowWidthSizeClass.Expanded -> {
            Row(modifier = Modifier.fillMaxWidth()) {
                PinYinMasterLearningPane(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    inits = distinctInitials,
                    onListItemClick = {
                        details = it
                    })
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .background(color = Color.Gray)
                )
                PinYinDetailsLearningPane(
                    modifier = Modifier.weight(1f),
                    finals = finals
                ) { final ->
                    learningViewModel.onFinalClick(details as String, final)
                }
            }
        }

        WindowWidthSizeClass.Compact -> {
            PinYinMasterLearningPane(
                modifier = Modifier.fillMaxSize(),
                inits = distinctInitials,
                onListItemClick = { init ->
                    learningViewModel.onInitialClick(init)
                }
            )
        }
    }
}