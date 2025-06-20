plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
}

android {
    namespace = "com.example.androidbasics"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androidbasics"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    dependencies {
        // Compose BOM
        implementation(platform("androidx.compose:compose-bom:2024.11.00"))
        implementation("androidx.activity:activity-compose:1.9.3")
        implementation("androidx.compose.material3:material3")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
        implementation("androidx.navigation:navigation-compose:2.8.4")

        // Room
        implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
        implementation("androidx.core:core-ktx:1.15.0")
        ksp("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
        implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")

        // Testing
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
        androidTestImplementation("androidx.test.ext:junit:1.2.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2024.11.00"))
    }
}