// Root-level build.gradle.kts — NOT an Android module

// No android plugin here!
plugins {
    // intentionally left blank
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

