@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.bsstokes.acme"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.bsstokes.acme"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeKtx)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.toolingPreview)
    implementation(libs.compose.material)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.testManifest)

    testImplementation(projects.testUtils)
    testImplementation(libs.compose.ui.test)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
}
