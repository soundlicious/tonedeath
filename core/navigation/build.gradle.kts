@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.tonedeath.android.library)
    alias(libs.plugins.tonedeath.android.hilt)
}

android {
    namespace = "dev.pabloexposito.navigation"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.androidx.compose.navigation)
}