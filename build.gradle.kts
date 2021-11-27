
plugins {
    id("com.android.application") apply false
    kotlin("android") apply false
    kotlin("kapt") apply false
    id("org.jetbrains.kotlin.android.extensions") apply false
    id("io.gitlab.arturbosch.detekt") version Dependencies.Detekt.Version
    id("org.jlleitschuh.gradle.ktlint") version Dependencies.Ktlint.GradlePluginVersion
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    detekt {
        config = rootProject.files("config/detekt/detekt.yml")
        autoCorrect = true
    }

    ktlint {
        ktlint {
            version.set(Dependencies.Ktlint.Version)
            android.set(true)
            verbose.set(true)
            outputToConsole.set(true)
            ignoreFailures.set(false)
            enableExperimentalRules.set(true)
            filter {
                exclude("**/generated/**")
                include("**/kotlin/**")
            }
        }
    }
}
