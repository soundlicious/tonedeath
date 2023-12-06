package dev.pabloexposito.tonedeath

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.pabloexposito.learning.LearningDetailsScreen
import dev.pabloexposito.learning.LearningScreen
import dev.pabloexposito.learning.ListeningScreen
import dev.pabloexposito.menu.MenuScreen
import dev.pabloexposito.navigation.BaseScreen
import dev.pabloexposito.navigation.LearningGraph
import dev.pabloexposito.navigation.MenuGraph
import dev.pabloexposito.navigation.PracticeGraph
import dev.pabloexposito.practice.GameOverScreen
import dev.pabloexposito.practice.PracticeScreen
import javax.inject.Inject


interface ScreenTitleResolver {
    operator fun invoke(screen: BaseScreen): String
}

internal class AppScreenTitleResolver @Inject constructor(
    @ApplicationContext private val context: Context
) : ScreenTitleResolver {
    override operator fun invoke(screen: BaseScreen): String = when (screen) {
        //region menu
        is MenuGraph,
        is MenuScreen -> context.getString(R.string.menu_screen_name)
        //endregion

        //region learning
        is LearningGraph,
        is LearningScreen -> context.getString(R.string.learning_screen_name)

        is LearningDetailsScreen -> context.getString(R.string.learning_details_screen_name)
        is ListeningScreen -> context.getString(R.string.listening_screen_name)
        //endregion

        //region practice
        is PracticeGraph,
        is PracticeScreen -> context.getString(R.string.practice_screen_name)
        is GameOverScreen -> context.getString(R.string.game_over_screen_name)
        //endregion

        else -> throw IllegalStateException("Unknown screen: $screen")
    }
}
