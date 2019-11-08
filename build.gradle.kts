// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildScript.gradle)
        classpath(BuildScript.kotlin)
        classpath(BuildScript.navigationSafeArgs)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

task("clean") {
    delete(rootProject.buildDir)
}
