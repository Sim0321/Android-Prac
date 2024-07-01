pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "kangAndroidLab"
include(":app")
include(":kakaosignup")
include(":app:call")
include(":call")
include(":stopwatch")
include(":messenger")
include(":notification")
include(":jetpark")
include(":jetpack")
include(":todo")
include(":calculator")
include(":webviewtest")
include(":addviewlist")
