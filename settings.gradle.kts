pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "tonedeath"

include(":app")
include(":core:model")
include(":core:common")
include(":core:data")
include(":feature:learning")
include(":feature:practice")
include(":core:designsystem")
include(":core:ui")
include(":feature:menu")
include(":core:navigation")
include(":core:testing")
