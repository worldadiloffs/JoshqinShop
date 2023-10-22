plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.joshqinshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.joshqinshop"
        minSdk = 24
        targetSdk = 33
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }

}
buildscript {

    repositories {

        mavenCentral()

    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //coil
    implementation("io.coil-kt:coil:2.4.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // GSON
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //okhttp
    implementation("com.squareup.okhttp3:okhttp:3.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.9.0")

    //gif
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.22")

    implementation ("nl.joery.animatedbottombar:library:1.1.0")

    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("io.coil-kt:coil:2.2.2")
}