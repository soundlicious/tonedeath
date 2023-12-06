@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.tonedeath.android.library)
    alias(libs.plugins.tonedeath.android.hilt)
}

android {
    namespace = "dev.pabloexposito.core.common"
}

dependencies {
    implementation(project(":core:model"))
}