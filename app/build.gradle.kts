@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.tonedeath.android.application)
    alias(libs.plugins.tonedeath.android.application.compose)
    alias(libs.plugins.tonedeath.android.hilt)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
}

android {
    namespace = "dev.pabloexposito.tonedeath"
    defaultConfig {
        applicationId = "dev.pabloexposito.tonedeath"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":feature:menu"))
    implementation(project(":feature:learning"))
    implementation(project(":feature:practice"))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.viewmodel)
    implementation(libs.androidx.compose.livedata)
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.materialWindow)
    implementation(libs.androidx.hilt.navigation.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
    kspTest(libs.hilt.compiler)
}