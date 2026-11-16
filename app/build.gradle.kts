plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.noorahlalbayt.tv"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.noorahlalbayt.tv"
        minSdk = 21
        targetSdk = 28
        versionCode = 1
        versionName = "1.0"
    }
}
