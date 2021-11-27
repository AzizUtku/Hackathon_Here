plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
}

android {
    compileSdk = Android.compileSdk
    buildToolsVersion = Android.buildTools

    defaultConfig {
        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = Android.testInstrumentationRunner
    }
    buildFeatures {
        dataBinding = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies{
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleVmKtx)
    implementation(AndroidX.lifecycleLivedataKtx)
    implementation(AndroidX.lifecycleViewmodelSavedState)
    implementation(AndroidX.constraintlayout)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.navigationFragmentKtx)
    implementation(AndroidX.navigationUiKtx)

    implementation(Kotlinx.coroutinesCore)
    implementation(Kotlinx.coroutinesPlayServices)

    implementation(Hilt.android)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

    kapt(Hilt.compiler)
    kapt(AndroidX.lifecycleCompiler)

    implementation(Google.material)
    implementation(Google.firebaseFirestore)
    implementation(Google.firebaseAuth)

    implementation(Other.glide)
    implementation(Other.lottie)
    implementation(Other.timber)

}
