package dev.pabloexposito.learning

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import dev.pabloexposito.designsystem.theme.AppTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.pabloexposito.data.repository.AppPinYinRepository
import dev.pabloexposito.ui.PinYinCard
import dev.pabloexposito.ui.DevicePreviews


@Composable
internal fun PinYinMasterLearningPane(modifier: Modifier = Modifier, inits : List<String>, onListItemClick: (element: String) -> Unit) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(inits) { index, init ->
            PinYinCard(init, index) { element ->
                onListItemClick(element)
            }
        }
    }
}

@DevicePreviews
@Composable
fun PreviewInitScreen() {
    AppTheme {
        PinYinMasterLearningPane(inits = AppPinYinRepository().getInitials(), modifier = Modifier.fillMaxWidth()) {}
    }
}