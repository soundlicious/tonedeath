import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import dev.pabloexposito.tonedeath.configureKotlinAndroid
import dev.pabloexposito.tonedeath.configurePrintApksTask
import dev.pabloexposito.tonedeath.disableUnnecessaryAndroidTests
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
//                configureFlavors(this)
//                configureGradleManagedDevices(this)
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                configurePrintApksTask(this)
                disableUnnecessaryAndroidTests(target)
            }
            dependencies {
//                add("testImplementation", kotlin("test"))
//                add("testImplementation", project(":core:testing"))
//                add("androidTestImplementation", kotlin("test"))
//                add("androidTestImplementation", project(":core:testing"))
            }
        }
    }
}
