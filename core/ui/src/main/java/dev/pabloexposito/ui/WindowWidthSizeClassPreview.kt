package dev.pabloexposito.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

sealed class WindowWidthSizeClassPreview(val value: WindowWidthSizeClass) {
    object Compact : WindowWidthSizeClassPreview(WindowWidthSizeClass.Compact)
    object Medium : WindowWidthSizeClassPreview(WindowWidthSizeClass.Medium)
    object Expanded : WindowWidthSizeClassPreview(WindowWidthSizeClass.Expanded)
}

class WindowWidthSizePreviewParameterProvider :
    PreviewParameterProvider<WindowWidthSizeClassPreview> {
    override val values: Sequence<WindowWidthSizeClassPreview> =
        sequenceOf(
            WindowWidthSizeClassPreview.Expanded,
            WindowWidthSizeClassPreview.Compact,
//            WindowWidthSizeClassPreview.Medium,
        )
}