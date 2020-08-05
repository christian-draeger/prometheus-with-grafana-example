@file:Suppress("UNUSED_VARIABLE")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.50"
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    id("com.avast.gradle.docker-compose") version "0.9.4"
    id("com.adarshr.test-logger") version "2.0.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.13" apply false
    id("com.github.ben-manes.versions") version "0.27.0" apply false
}

val isIdea = System.getProperty("idea.version") != null

allprojects {

    apply(plugin = "com.adarshr.test-logger")

    testlogger {
        setTheme(if (isIdea) "plain" else "mocha")
        isShowFullStackTraces = false
        slowThreshold = 1000
    }

    apply(plugin = "se.patrikerdes.use-latest-versions")
    apply(plugin = "com.github.ben-manes.versions")

    repositories {
        jcenter()
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
                apiVersion = "1.3"
                languageVersion = "1.3"
                freeCompilerArgs = listOf("-Xjsr305=strict", "-progressive")
            }
        }

        withType<Test>().configureEach {
            shouldRunAfter("useLatestVersions")
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        val useLatestVersions by getting {
            dependsOn("useLatestVersionsCheck")
        }

        val updateDependencies by creating {
            dependsOn(useLatestVersions)
        }
    }
}

tasks {

    val composeUp by getting {
        dependsOn(":application:build")
    }
}