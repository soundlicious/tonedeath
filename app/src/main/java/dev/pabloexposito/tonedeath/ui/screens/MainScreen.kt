package dev.pabloexposito.tonedeath.ui.screens

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
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
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(navigator: AppNavigator, windowSizeClass: WindowSizeClass) {
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(appBarTitle ?: stringResource(id = R.string.app_name)) },
                modifier = Modifier
                    .fillMaxWidth(),
                navigationIcon = if (showBackButtonState == true) {
                    {
                        IconButton(onClick = { navigator.navigateUp() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                } else {
                    {}
                }
            )
        }) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = MenuGraph.path,
            modifier = Modifier.padding(contentPadding)
        ) {
            practiceGraph(windowSizeClass)
            menuGraph()
            learningGraph(windowSizeClass)
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainMenuScreenPreview() {
    AppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("ToneDeath") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    navigationIcon = if (true) {
                        {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    } else {
                        {}
                    }
                )
            }) {

        }
    }
}