@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.tonedeath.android.library)
    alias(libs.plugins.tonedeath.android.library.compose)

}

android {
    namespace = "dev.pabloexposito.ui"
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.windowSizeClass)
    api(libs.androidx.compose.ui.tooling.preview)

    debugApi(libs.androidx.compose.ui.tooling)


    implementation(libs.androidx.constraintlayout.compose)
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
}