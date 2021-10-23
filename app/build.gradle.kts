plugins {
    id ("com.android.application")
    id( "dagger.hilt.android.plugin")
    kotlin( "android")
    kotlin( "kapt")
}

//repositories {
//    google()
//    mavenCentral()
//}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "ru.bmstu.mobile.crypto"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
    }
}

dependencies {

    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.livedata)
    implementation(Dependencies.AndroidX.viewModel)
    implementation(Dependencies.AndroidX.preferences)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.recycler)
    implementation(Dependencies.Other.material)

    // fragment
    implementation (Dependencies.Navigation.navigationFragment)
    implementation (Dependencies.Navigation.navigationUi)
    implementation (Dependencies.Other.vbPropertyDelegate)

    // coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    // di
    implementation (Dependencies.Dagger.dagger)
    kapt (Dependencies.Dagger.daggerCompiler)
    implementation (Dependencies.Dagger.daggerHilt)
    kapt (Dependencies.Dagger.hiltCompiler)

    // json
    implementation( "com.squareup.moshi:moshi-kotlin:1.12.0")
    kapt( "com.squareup.moshi:moshi-kotlin-codegen:1.12.0")

    // network
    implementation(Dependencies.Network.sandwich)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.interceptor)
    implementation(Dependencies.Network.moshi)
    implementation(Dependencies.Network.coil)

    // compose
    implementation(Dependencies.Compose.activity)
    implementation (Dependencies.Compose.animations)
    implementation( Dependencies.Compose.material)
    implementation( Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.viewModels)
    implementation(Dependencies.Compose.systemUiController)

    // time
    implementation( "joda-time:joda-time:2.10.12")

    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}