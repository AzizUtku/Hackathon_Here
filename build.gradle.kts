buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.googleServices)
        classpath(Build.hiltAndroid)
        classpath(Build.navigationSafeArgs)
        classpath(Build.mapsPlatform)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
