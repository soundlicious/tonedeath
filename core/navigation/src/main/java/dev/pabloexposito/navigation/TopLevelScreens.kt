package dev.pabloexposito.navigation

data object MenuGraph : BaseScreen.Graph {
    override val root: String
        get() = "menu_graph"
}

data object PracticeGraph : BaseScreen.Graph {
    override val root: String
        get() = "practice_graph"
}

data object LearningGraph: BaseScreen.Graph {
    override val root: String
        get() = "learning_graph"
}