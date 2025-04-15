plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") // Add this line for Firebase & Google services
}

android {
    namespace = "com.example.appactivitylifecycle"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.appactivitylifecycle"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Add Firebase Authentication and Google Sign-In dependencies
    implementation("com.google.firebase:firebase-auth:22.3.1") // Firebase Authentication
    implementation("com.google.android.gms:play-services-auth:20.7.0") // Google Sign-In

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
