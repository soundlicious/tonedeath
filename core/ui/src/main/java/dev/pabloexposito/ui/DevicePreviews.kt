package dev.pabloexposito.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@LandscapeDevicePreviews
@PortraitDevicePreviews
annotation class DevicePreviews


@Preview(
    name = "phone-landscape",
    device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    group = "dark"
)
@Preview(
    name = "foldable-landscape",
    device = "spec:shape=Normal,width=841,height=673,unit=dp,dpi=480",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    group = "dark"
)
@Preview(
    name = "tablet-landscape",
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    group = "dark"
)
@Preview(
    name = "fold4Outer-landscape",
    device = "spec:shape=Normal,width=1680,height=720,unit=dp,dpi=480",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    group = "dark"
)
annotation class LandscapeDevicePreviews



@Preview(
    name = "phone-portrait",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    group = "dark"
)
@Preview(
    name = "foldable-portrait",
    device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    group = "dark"
)
@Preview(
    name = "tablet-portrait",
    device = "spec:shape=Normal,width=800,height=1280,unit=dp,dpi=480",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    group = "dark"
)
@Preview(
    name = "fold4Outer-portrait",
    device = "spec:shape=Normal,width=720,height=1680,unit=dp,dpi=480",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    group = "dark"
)
annotation class PortraitDevicePreviews