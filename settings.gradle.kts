//pluginManagement {
//    repositories {
//        gradlePluginPortal()
//        jcenter()
//        google()
//        mavenCentral()
//    }
//    resolutionStrategy {
//        eachPlugin {
//            if (requested.id.id.startsWith("com.android")) {
//                useModule("com.android.tools.build:gradle:7.0.2")
//            }
//            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
//                useVersion("1.5.31")
//            }
//            if (requested.id.id.startsWith("dagger.hilt.android")) {
//                useModule("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
//            }
//        }
//    }
//}

dependencyResolutionManagement {
    repositoriesMode.set(org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
}

rootProject.name = "CryptoCompose"
include(":app")
include(":app:chart")
