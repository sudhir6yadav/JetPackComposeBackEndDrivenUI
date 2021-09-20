plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.4.0"
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.hadi.superherolexicon"
        minSdk = 21
        targetSdk = 31
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["composeVersion"] as String
        kotlinCompilerVersion = "1.5.10"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")

    implementation("androidx.compose.ui:ui:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.material:material-icons-extended:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.runtime:runtime:1.1.0-alpha04")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.0-alpha04")

    // implementation "androidx.compose.material:material-icons-extended:$compose_version"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")

    // KotlinX Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${rootProject.extra["kotlinSerializationVersion"]}")

    // Coil Image Loading
    implementation("io.coil-kt:coil-compose:1.3.0")

    // Flow Layout
    implementation("com.google.accompanist:accompanist-flowlayout:0.14.0")
    implementation("androidx.activity:activity-ktx:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")

    implementation("com.google.code.gson:gson:2.8.7")

    //okHttp
    implementation("com.squareup.okhttp3:okhttp:4.3.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.3.1")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.7.1")
    implementation("com.squareup.retrofit2:converter-moshi:2.7.1")


    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["composeVersion"]}")
    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra["composeVersion"]}")
}