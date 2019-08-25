object Versions {
    val kotlin = "1.3.50"
    object AndroidX {
        val appCompat = "1.0.2"
        val core = "1.0.2"
        val constraintLayout = "1.1.3"
    }
    object Rx {
        val rxJava2 = "2.2.11"
        val rxAndroid2 = "2.1.1"
    }
}

object Config {
    val applicationId = "dev.rivu.nasaapodarchive"
    val minSdkVersion = 19
    val targetSdkVersion = 28
    val versionCode = 1
    val versionName = "1.0"
}

object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val AndroidX = object {
        val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        val core = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
    }
    val Rx = object {
        val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.Rx.rxJava2}"
        val rxAndroid2 = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.rxAndroid2}"
    }
}