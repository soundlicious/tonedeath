package dev.pabloexposito.menu

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.pabloexposito.common.SCREEN_NAME_PARAMETER
import dev.pabloexposito.designsystem.theme.AppTheme
import dev.pabloexposito.navigation.BaseScreen
import dev.pabloexposito.ui.LandscapeDevicePreviews
import dev.pabloexposito.ui.PortraitDevicePreviews

data object MenuScreen : BaseScreen.Screen {
    override val root: String
        get() = "menu"
}

internal fun NavGraphBuilder.menuDestination() {
    composable(
        route = MenuScreen.path,
        arguments = listOf(
            navArgument(SCREEN_NAME_PARAMETER) {
                type = NavType.StringType
            }
        )
    ) {
        MenuRoute(configuration = LocalConfiguration.current)
    }
}

@Composable
private fun MenuRoute(
    configuration: Configuration,
    menuViewModel: MenuViewModel = hiltViewModel()
) {
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    if (isLandscape) {
        LandscapeMenu(
            modifier = Modifier.fillMaxSize(),
            onLearningClick = menuViewModel::onLearningClick,
            onPracticeClick = menuViewModel::onPracticeClick
        )
    } else {
        PortraitMenu(
            modifier = Modifier.fillMaxSize(),
            onLearningClick = menuViewModel::onLearningClick,
            onPracticeClick = menuViewModel::onPracticeClick
        )
    }
}

@Composable
private fun LandscapeMenu(
    onLearningClick: () -> Unit,
    modifier: Modifier = Modifier,
    onPracticeClick: () -> Unit,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.sakura),
            contentDescription = null,
            modifier = Modifier
                .align(CenterStart)
                .fillMaxHeight(),
            contentScale = ContentScale.FillHeight
        )
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            MenuButton(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(CenterHorizontally),
                title = stringResource(id = R.string.menu_learning),
                painter = painterResource(R.drawable.learning),
                onClick = onLearningClick
            )

            MenuButton(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(CenterHorizontally),
                title = stringResource(id = R.string.menu_practice),
                painter = painterResource(R.drawable.practice),
                onClick = onPracticeClick
            )
        }
    }
}

@LandscapeDevicePreviews
@Composable
private fun LandscapeMenuPreview() {
    AppTheme {
        LandscapeMenu(modifier = Modifier.fillMaxSize(), onLearningClick = {}, onPracticeClick = {})
    }
}

@Composable
private fun PortraitMenu(
    onLearningClick: () -> Unit,
    modifier: Modifier = Modifier,
    onPracticeClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.sakura),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
        )

        MenuButton(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(CenterHorizontally),
            title = stringResource(id = R.string.menu_learning),
            painter = painterResource(R.drawable.learning),
            onClick = onLearningClick
        )

        MenuButton(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(CenterHorizontally),
            title = stringResource(id = R.string.menu_practice),
            painter = painterResource(R.drawable.practice),
            onClick = onPracticeClick
        )
    }
}

@PortraitDevicePreviews
@Composable
private fun PortraitMenuPreview() {
    AppTheme {
        PortraitMenu(modifier = Modifier.fillMaxSize(), onLearningClick = {}, onPracticeClick = {})
    }
}

@Composable
private fun MenuButton(
    title: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FilledIconButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painter,
                contentDescription = null
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = title
            )
        }
    }
}
