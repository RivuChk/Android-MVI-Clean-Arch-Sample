package dev.rivu.nasaapodarchive

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dagger.android.support.DaggerAppCompatActivity
import dev.rivu.nasaapodarchive.apoddetails.ApodDetailFragment
import dev.rivu.nasaapodarchive.apodlist.ApodListFragment
import dev.rivu.nasaapodarchive.domain.utils.format
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData
import dev.rivu.nasaapodarchive.utils.DetailsTransition
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.Build.VERSION
import android.os.Build.VERSION.SDK_INT
import androidx.core.content.ContextCompat.getSystemService
import androidx.transition.Fade


class MainActivity : DaggerAppCompatActivity() {

    val apodListFragment by lazy {
        ApodListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        apodListFragment.showApodImageDetail = ::showApodImageDetail
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, apodListFragment)
            .commit()
    }

    private fun showApodImageDetail(view: View, apodViewData: ApodViewData) {
        val apodDetailFragment = ApodDetailFragment()
        if (SDK_INT >= LOLLIPOP) {
            apodDetailFragment.sharedElementEnterTransition = DetailsTransition()
            apodDetailFragment.sharedElementReturnTransition = DetailsTransition()
        }
        val bundle = Bundle()
        bundle.putParcelable("apodViewData",apodViewData)
        apodDetailFragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, apodDetailFragment)
            .addSharedElement(view, apodViewData.date.format())
            .addToBackStack("detail")
            .commit()
    }
}
