rootProject.name = "prometheus-with-grafana-example"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

include("application", "systemTests")
