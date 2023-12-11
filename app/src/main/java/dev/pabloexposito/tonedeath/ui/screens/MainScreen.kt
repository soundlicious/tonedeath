package dev.pabloexposito.tonedeath.ui.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.pabloexposito.learning.learningGraph
import dev.pabloexposito.menu.menuGraph
import dev.pabloexposito.navigation.MenuGraph
import dev.pabloexposito.practice.practiceGraph
import dev.pabloexposito.tonedeath.AppNavigator
import dev.pabloexposito.tonedeath.R

@Composable
fun MainRoute(
    navigator: AppNavigator,
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    var backPressedDispatcher =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigator.navigateUp()
            }
        }
    }
    LaunchedEffect(key1 = navController) {
        navigator.navController = navController
        backPressedDispatcher?.addCallback(backCallback)
        navController.addOnDestinationChangedListener { _, _, _ ->
            navigator.onDestinationChanged()
        }
    }
    DisposableEffect(key1 = navController) {
        onDispose {
            backCallback.remove()
            navigator.navController = null
            backPressedDispatcher = null
        }
    }

    val showBackButtonState by navigator.showBackButton.observeAsState()
    val appBarTitle by navigator.appBarTitle.observeAsState()

    MainScreen(
        title = { Text(appBarTitle ?: stringResource(id = R.string.app_name)) },
        modifier = modifier,
        navigationIcon = {
            if (showBackButtonState == true)
                TopLevelNavigation { navigator.navigateUp() }
        },
        content = { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = MenuGraph.path,
                modifier = Modifier.padding(contentPadding)
            ) {
                practiceGraph()
                menuGraph()
                learningGraph(windowSizeClass)
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MainScreen(
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = title,
                modifier = Modifier
                    .fillMaxWidth(),
                navigationIcon = navigationIcon
            )
        },
        content = content
    )
}

@Composable
private fun TopLevelNavigation(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_navigation)
        )
    }
}

@Preview
@Composable
private fun MainMenuScreenPreview() {
    AppTheme {
        MainScreen(
            title = {},
            navigationIcon = {},
            modifier = Modifier.fillMaxSize(),
            content = {}
        )
    }
}