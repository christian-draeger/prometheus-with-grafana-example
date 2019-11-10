plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("com.bmuschko.docker-spring-boot-application") version "5.3.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

fun springBoot(partialModule: String) = "org.springframework.boot:spring-boot-$partialModule"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(springBoot("starter-actuator"))
    implementation(springBoot("starter-web"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")
    implementation("io.github.microutils:kotlin-logging:1.7.2")
    implementation("io.micrometer:micrometer-core")
    implementation("io.micrometer:micrometer-registry-prometheus")

    // testing
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("it.skrape:skrapeit-mockmvc:+")
    testImplementation("io.strikt:strikt-core:0.21.1")
    testImplementation("io.mockk:mockk:1.9.3")
}

tasks {

    build {
        finalizedBy(dockerBuildImage)
    }
    
    bootRun {
        args("--spring.profiles.active=local")
    }
}

docker {
    springBootApplication {
        baseImage.set("openjdk:8-alpine")
        maintainer.set(rootProject.name)
        tag.set("${rootProject.name}:latest")
        jvmArgs.set(listOf("-Dspring.profiles.active=production", "-Xmx2048m"))
    }
}
