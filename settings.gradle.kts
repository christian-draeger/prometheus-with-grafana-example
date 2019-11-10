rootProject.name = "daimler-ocr-middleware"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

include("application", "monitoring", "systemTests")
