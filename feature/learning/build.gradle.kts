@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.tonedeath.android.feature)
    alias(libs.plugins.tonedeath.android.library.compose)
    alias(libs.plugins.tonedeath.android.hilt)
}

android {
    namespace = "dev.pabloexposito.learning"
}

dependencies {
}