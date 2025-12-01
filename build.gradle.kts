// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
//    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"

//    kotlin("android") version "1.8.22" apply false
//    id("com.android.application") version "8.1.1" apply false
//    id("com.android.library") version "8.1.1" apply false
//    id("org.jetbrains.compose") version "1.5.3" apply false
//    id("dagger.hilt.android.plugin") version "2.51.1" apply false

}