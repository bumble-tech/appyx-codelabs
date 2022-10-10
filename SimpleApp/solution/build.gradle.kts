plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.bumble.appyx_codelabs.simple_app.solution"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {

    // Core
    implementation(libs.appyx.core)

    // Test rules and utility classes for testing on Android
    androidTestImplementation(libs.appyx.testing.ui)

    // Utility classes for unit testing
    testImplementation(libs.appyx.testing.ui.common)

    // Test rules and utility classes for unit testing using JUnit4
    testImplementation(libs.appyx.testing.junit4)

    // Test extensions and utility classes for unit testing using JUnit5
    testImplementation(libs.appyx.testing.junit5)

    implementation(libs.androidx.core)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.compose.ui.preview)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
