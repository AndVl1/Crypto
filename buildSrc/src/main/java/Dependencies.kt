
object Dependencies {

    object Ktlint {
        const val GradlePluginVersion = "10.2.0"
        const val Version = "0.42.1"
    }

    object Detekt {
        const val Version = "1.18.1"
    }

    object Compose {
        const val Version = "1.0.4"
        const val Accompanist = "0.20.0"

        // Integration with activities
        const val activity = "androidx.activity:activity-compose:1.3.1"
        // Compose Material Design
        const val material = "androidx.compose.material:material:$Version"
        // Animations
        const val animations = "androidx.compose.animation:animation:$Version"
        // Tooling support (Previews, etc.)
        const val tooling = "androidx.compose.ui:ui-tooling:$Version"
        // Integration with ViewModels
        const val viewModels = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-rc01"
        const val navigation = "androidx.navigation.navigation-compose:2.4.0-alpha04"
        const val viewBinding = "androidx.compose.ui:ui-viewbinding:$Version"
        // Accompanist
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$Accompanist"
        const val animatedNavigation = "com.google.accompanist:accompanist-navigation-animation:$Accompanist"
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$Accompanist"
    }

    object Network {
        const val retrofitVersion = "2.9.0"
        const val coilVersion = "1.4.0"

        const val sandwich = "com.github.skydoves:sandwich:1.2.1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
        const val moshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
        const val coil = "io.coil-kt:coil:$coilVersion"
    }

    object AndroidX {
        const val lifecycleVersion = "2.3.1"
        const val recyclerVersion = "1.2.1"

        const val core ="androidx.core:core-ktx:1.6.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val preferences = "androidx.preference:preference-ktx:1.1.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val recycler = "androidx.recyclerview:recyclerview:$recyclerVersion"
    }

    object Other {
        const val materialVersion = "1.4.0"

        const val material = "com.google.android.material:material:$materialVersion"
        const val vbPropertyDelegate = "com.github.kirich1409:viewbindingpropertydelegate:1.5.0-beta02"
    }

    object Navigation {
        const val navigationVersion = "2.3.5"

        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUi = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    }

    object Dagger {
        const val version = "2.38.1"

        const val dagger = "com.google.dagger:dagger:$version"
        const val daggerHilt = "com.google.dagger:hilt-android:$version"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
    }

    object Plugins {
        const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.3"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.38.1"
    }
}
