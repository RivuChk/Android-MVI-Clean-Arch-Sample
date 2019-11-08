object Versions {
    const val gradle = "3.5.2"
    const val kotlin = "1.3.50"
    const val dagger = "2.20"
    const val retrofit = "2.6.1"
    const val okHttp = "4.1.0"
    const val room = "2.1.0"
    const val lifecycle = "2.0.0"
    const val glide = "4.9.0"
    object AndroidX {
        const val appCompat = "1.0.2"
        const val core = "1.0.2"
        const val constraintLayout = "1.1.3"
        const val recyclerView = "1.0.0"
        const val cardView = "1.0.0"
        const val legacySupport = "1.0.0"
        const val material = "1.0.0"
        const val navigation = "2.1.0"
    }
    object Rx {
        const val rxJava2 = "2.2.11"
        const val rxAndroid2 = "2.1.1"
        const val rxBinding = "3.0.0"
    }
    object Test {
        const val junit = "4.12"
        const val androidTestRunner = "1.2.0"
        const val espresso = "3.2.0"
        const val hamcrest = "1.3"
        const val mockito = "1.10.19"
        const val mockitoKotlin = "1.5.0"
        const val androidxTestExt = "1.1.1"
    }
    const val touchImageView = "2.2.0"
}

object Config {
    const val applicationId = "dev.rivu.nasaapodarchive"
    const val minSdkVersion = 19
    const val targetSdkVersion = 28
    const val versionCode = 1
    const val versionName = "1.0"
}

object BuildScript {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.AndroidX.navigation}"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val core = "androidx.core:core-ktx:${Versions.AndroidX.core}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerView}"
        const val cardView = "androidx.cardview:cardview:${Versions.AndroidX.cardView}"
        const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.AndroidX.legacySupport}"
        const val material = "com.google.android.material:material:${Versions.AndroidX.material}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
    }
    object Rx {
        const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.Rx.rxJava2}"
        const val rxAndroid2 = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.rxAndroid2}"
    }
    object Dagger {
        const val android = "com.google.dagger:dagger-android:${Versions.dagger}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val annotationProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }
    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val androidTestRunner = "androidx.test:runner:${Versions.Test.androidTestRunner}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
        const val mockito = "org.mockito:mockito-core:${Versions.Test.mockito}"
        const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.Test.mockitoKotlin}"
        const val hamcrest = "org.hamcrest:hamcrest-all:${Versions.Test.hamcrest}"
        const val androidxTestExt = "androidx.test.ext:junit:${Versions.Test.androidxTestExt}"
    }
    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val rxJava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }
    object Database {
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomAnnotation = "androidx.room:room-compiler:${Versions.room}"
        const val roomRx2 = "androidx.room:room-rxjava2:${Versions.room}"
        const val roomTest = "androidx.room:room-testing:${Versions.room}"
    }
    object AndroidArch {
        const val viewModelLiveData = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
        const val livedataRx = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.lifecycle}"
        const val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    }
    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }
    object RxBinding {
        const val core = "com.jakewharton.rxbinding3:rxbinding-core:${Versions.Rx.rxBinding}"
        const val recyclerview = "com.jakewharton.rxbinding3:rxbinding-recyclerview:${Versions.Rx.rxBinding}"
        const val swiperefreshlayout = "com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:${Versions.Rx.rxBinding}"
    }
    const val touchImageView = "com.github.MikeOrtiz:TouchImageView:${Versions.touchImageView}"
}