plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.9.24"
    kotlin("kapt")
}

android {
    namespace = "data.sahonmu.burger87"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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


    flavorDimensions += "version"
    productFlavors {
        create("live") {
            dimension = "version"
//            buildConfigField("String", "SUPABASE_API_KEY", "sb_publishable_mxRTEpx_4uwsRvUjU0w1JQ_3NS5Jzbe")
//            buildConfigField("String", "SUPABASE_API_URL", "\"https://qgdnsxoaozeipuxcltka.supabase.co\"")
        }

        create("dev") {
            dimension = "version"
//            buildConfigField("String", "SUPABASE_API_KEY", "sb_publishable_mxRTEpx_4uwsRvUjU0w1JQ_3NS5Jzbe")
//            buildConfigField("String", "SUPABASE_API_URL", "\"https://qgdnsxoaozeipuxcltka.supabase.co\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    kapt("androidx.hilt:hilt-compiler:1.3.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Supabase
    implementation(platform("io.github.jan-tennert.supabase:bom:2.6.1"))
    implementation("io.github.jan-tennert.supabase:supabase-kt")
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.github.jan-tennert.supabase:storage-kt")

    implementation("io.ktor:ktor-client-android:2.3.0")
    implementation("io.ktor:ktor-utils:2.3.0")
    implementation("io.ktor:ktor-client-core:2.3.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.2.0")
}


