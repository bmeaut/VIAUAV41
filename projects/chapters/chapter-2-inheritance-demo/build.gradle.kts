plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
    kotlin("jvm") version "2.0.20"
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

javafx {
    version = "17"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application.mainClass = "com.example.MainKt"
