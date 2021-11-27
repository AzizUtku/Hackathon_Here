object Build {

    private const val androidBuildToolsVersion = "7.1.0-alpha03"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    const val hiltAndroid = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.hiltVersion}"

    const val googleServices = "com.google.gms:google-services:${Google.servicesVersion}"

    const val navigationSafeArgs
        = "androidx.navigation:navigation-safe-args-gradle-plugin:${AndroidX.navigationVersion}"

    const val mapsPlatform
        = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Google.mapsPlatformVersion}"

}