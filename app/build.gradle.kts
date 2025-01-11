plugins {
    id("com.android.application")
}

android {
    namespace = "com.order.cartejeu"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.order.cartejeu"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // Activer View Binding
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.activity:activity:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    // Retrofit dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp for logging network requests
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // AndroidX Biometric
    implementation("androidx.biometric:biometric:1.1.0")

    // Unit Testing dependencies
    testImplementation("junit:junit:4.13.2")

    // Android Testing dependencies
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
