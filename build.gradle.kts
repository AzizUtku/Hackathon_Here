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
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
