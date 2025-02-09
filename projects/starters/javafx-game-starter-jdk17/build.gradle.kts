plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
    kotlin("jvm") version "2.1.10"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

javafx {
    version = "17"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application.mainClass = "com.example.MainKt"
