object Versions {
    val kotlin = "1.3.50"
    object AndroidX {
        val appCompat = "1.0.2"
        val core = "1.0.2"
        val constraintLayout = "1.1.3"
    }
}

object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val AndroidX = object {
        val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        val core = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
    }
}