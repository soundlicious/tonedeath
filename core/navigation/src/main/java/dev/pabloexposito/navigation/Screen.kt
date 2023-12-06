package dev.pabloexposito.navigation

import dev.pabloexposito.common.SCREEN_NAME_PARAMETER
import kotlin.text.StringBuilder


interface ScreenParams {
    val paramsList: Map<String, String>
        get() = mapOf()
    val queryList: Map<String, String>
        get() = mapOf()
}

sealed interface BaseScreen  {
    interface Graph : BaseScreen
    interface Screen: BaseScreen

    val orderedParameters: List<String>
        get() = listOf()
    val queryParams: List<String>
        get() = listOf()
    val root: String
    val path: String
        get() = this@BaseScreen.getScreenPath()

    fun getScreenPath(): String {
        val path = StringBuilder(root)
        orderedParameters.forEach { param ->
            path.append("/{$param}")
        }
        path.append("?")
        (listOf(SCREEN_NAME_PARAMETER) + queryParams).forEachIndexed { index, query ->
            path.append("$query={$query}")
            if (index < queryParams.size - 1) {
                path.append("&")
            }
        }
        return path.toString()
    }

    fun getRoute(
        parameters: Map<String, String> = mapOf(),
        queries: Map<String, String> = mapOf()
    ): String {
        if (!parameters.keys.containsAll(orderedParameters))
            throw IllegalArgumentException("Parameters ${parameters.keys} must match Screen Parameters : $orderedParameters")

        var rootDestination = path
        parameters.forEach { (key, value) ->
            rootDestination = rootDestination.replaceFirst("/{$key}", "/$value")
        }
        queries.forEach {
            rootDestination = rootDestination.replaceFirst("={${it.key}}", "=${it.value}")
        }
        return rootDestination
    }

    fun getRoute(screenParams: ScreenParams? = null, title: String? = null): String {
        return getRoute(
            parameters = screenParams?.paramsList ?: mapOf(),
            queries = mutableMapOf<String, String>().apply {
                screenParams?.queryList?.let { putAll(it) }
                put(SCREEN_NAME_PARAMETER, title.orEmpty())
            }
        )
    }
}