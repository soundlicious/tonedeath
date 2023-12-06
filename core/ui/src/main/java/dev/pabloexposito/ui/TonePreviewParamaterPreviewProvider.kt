package dev.pabloexposito.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.pabloexposito.model.data.Tone

class TonePreviewParameterProvider: PreviewParameterProvider<Tone> {
    override val values: Sequence<Tone>
        get() = Tone.values().asSequence()
}