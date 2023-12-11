package dev.pabloexposito.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ToneCardsLayoutPreviewProvider: PreviewParameterProvider<ToneCardsLayout> {
    override val values: Sequence<ToneCardsLayout>
        get() = ToneCardsLayout.values().asSequence()
}