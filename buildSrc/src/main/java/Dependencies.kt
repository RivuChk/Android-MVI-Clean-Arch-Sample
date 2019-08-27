object Versions {
    val kotlin = "1.3.50"
    val dagger = "2.20"
    val retrofit = "2.6.1"
    val okHttp = "4.1.0"
    val room = "2.1.0"
    val lifecycle = "2.0.0"
    object AndroidX {
        val appCompat = "1.0.2"
        val core = "1.0.2"
        val constraintLayout = "1.1.3"
    }
    object Rx {
        val rxJava2 = "2.2.11"
        val rxAndroid2 = "2.1.1"
    }
    object Test {
        val junit = "4.12"
        val androidTestRunner = "1.2.0"
        val espresso = "3.2.0"
        val hamcrest = "1.3"
        val mockito = "1.10.19"
        val mockitoKotlin = "1.5.0"
        val androidxTestExt = "1.1.1"
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
    val Dagger = object {
        val android = "com.google.dagger:dagger-android:${Versions.dagger}"
        val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        val annotationProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }
    val Test = object {
        val junit = "junit:junit:${Versions.Test.junit}"
        val androidTestRunner = "androidx.test:runner:${Versions.Test.androidTestRunner}"
        val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
        val mockito = "org.mockito:mockito-core:${Versions.Test.mockito}"
        val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.Test.mockitoKotlin}"
        val hamcrest = "org.hamcrest:hamcrest-all:${Versions.Test.hamcrest}"
        val androidxTestExt = "androidx.test.ext:junit:${Versions.Test.androidxTestExt}"
    }
    val Network = object {
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        val rxJava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }
    val Database = object {
        val room = "androidx.room:room-runtime:${Versions.room}"
        val roomAnnotation = "androidx.room:room-compiler:${Versions.room}"
        val roomRx2 = "androidx.room:room-rxjava2:${Versions.room}"
        val roomTest = "androidx.room:room-testing:${Versions.room}"
    }
    val AndroidArch = object {
        val viewModelLiveData = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
        val livedataRx = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.lifecycle}"
        val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    }
}