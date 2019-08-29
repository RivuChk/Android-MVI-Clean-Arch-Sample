package dev.rivu.nasaapodarchive.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.rivu.nasaapodarchive.MainActivity
import dev.rivu.nasaapodarchive.apodlist.ApodListFragment

@Module
abstract class AndroidComponentsBindingModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}