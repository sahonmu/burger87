plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    kotlin("kapt")

//    alias(libs.plugins.secrets.gradle.plugin)
}

android {
    namespace = "com.sahonmu.burger87"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.sahonmu.burger87"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }

    flavorDimensions += "version"
    productFlavors {
        create("live") {
            dimension = "version"
//            versionCode = 1
//            versionName = "1.0.0"
            manifestPlaceholders["appNameGradle"] = "@string/app_name"
            manifestPlaceholders["appLabel"] = "@string/app_name"
            resValue("string", "google_maps_api_key", "AIzaSyDmcAQ5GqHyUR3zBH_sQgmeXTo3da8IV6o")
        }

        create("dev") {
            dimension = "version"
            versionCode = 1
            versionName = "1.3.0"
            applicationIdSuffix = ".dev"
            manifestPlaceholders["appNameGradle"] = "@string/app_name"
            manifestPlaceholders["appLabel"] = "@string/app_name"
            resValue("string","app_name","버거87(DEV)")
            resValue("string", "google_maps_api_key", "AIzaSyDmcAQ5GqHyUR3zBH_sQgmeXTo3da8IV6o")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.25.1")

    // GoogleMap
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.maps.android:maps-compose:2.12.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    // Fiarbase
    implementation(platform("com.google.firebase:firebase-bom:34.6.0"))
    implementation("com.google.firebase:firebase-analytics")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.52")
    kapt("androidx.hilt:hilt-compiler:1.3.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Log
    implementation("com.jakewharton.timber:timber:5.0.1")
}