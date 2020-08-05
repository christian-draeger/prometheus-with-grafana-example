plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    val jUnitVersion = "5.5.2"
    val fluentleniumVersion = "3.8.1"
    val seleniumVersion = "3.141.59"
    val webdriverManagerVersion = "3.8.1"
    val awaitilityVersion = "4.0.2"
    val kotlinLoggerVersion = "1.7.9"
    val julToSlf4jVersion = "1.7.28"

    testImplementation(
        group = "org.junit.jupiter",
        name = "junit-jupiter",
        version = jUnitVersion
    )

    testImplementation(
        group = "org.seleniumhq.selenium",
        name = "selenium-java",
        version = seleniumVersion
    )
    testImplementation(
        group = "io.github.bonigarcia",
        name = "webdrivermanager",
        version = webdriverManagerVersion
    )

    testImplementation(
        group = "org.fluentlenium",
        name = "fluentlenium-junit-jupiter",
        version = fluentleniumVersion
    )
    testImplementation(
        group = "org.fluentlenium",
        name = "fluentlenium-assertj",
        version = fluentleniumVersion
    )
    testImplementation(
        group = "org.awaitility",
        name = "awaitility-kotlin",
        version = awaitilityVersion
    )
    testImplementation(
        group = "io.github.microutils",
        name = "kotlin-logging",
        version = kotlinLoggerVersion
    )
    testImplementation(
        group = "org.slf4j",
        name = "jul-to-slf4j",
        version = julToSlf4jVersion
    )

    tasks {
        test {
            dependsOn(":composeUp")
            finalizedBy(":composeDown")
            systemProperty("browser", System.getProperty("browser"))
        }
    }
}
