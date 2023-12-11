@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.tonedeath.android.library)
    alias(libs.plugins.tonedeath.android.library.compose)
    alias(libs.plugins.tonedeath.android.hilt)
}

android {
    namespace = "dev.pabloexposito.testing"
}

dependencies {
    api(libs.kotlinx.coroutines.test)
    api(libs.hilt.android.testing)
    api(libs.junit)
    api(libs.junit.jupiter)
    api(libs.androidx.core.testing)

    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":core:data"))
}