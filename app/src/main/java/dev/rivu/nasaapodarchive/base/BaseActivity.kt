package dev.rivu.nasaapodarchive.base

import android.app.Activity
import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        bind()
    }

    protected abstract fun initView()

    protected abstract fun bind()

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    @LayoutRes
    protected abstract fun layoutId(): Int
}