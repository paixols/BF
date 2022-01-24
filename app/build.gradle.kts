plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildTools

    defaultConfig {
        applicationId = AppConfig.appId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = Testing.instrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    Android.androidDependencies.forEach { dependencies -> implementation(dependencies) }
    KotlinLibs.implementations.forEach { dependency -> implementation(dependency) }
    KotlinLibs.annotationProcessing.forEach { dependencies -> kapt(dependencies) }
    Google.google.forEach { dependencies -> implementation(dependencies) }
    DependencyInjection.apply {
        kaptImplementations.forEach { dependency -> kapt(dependency) }
        implementations.forEach { dependency -> implementation(dependency) }
    }
    Testing.testImplementation.forEach { dependencies -> testImplementation(dependencies) }
    Testing.androidTestImplementation.forEach { dependencies ->
        androidTestImplementation(
            dependencies
        )
    }
}