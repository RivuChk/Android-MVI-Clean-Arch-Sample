package dev.rivu.nasaapodarchive.apoddetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import dev.rivu.nasaapodarchive.R
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData
import dev.rivu.nasaapodarchive.utils.load
import kotlinx.android.synthetic.main.fragment_apod_detail.*

class ApodDetailFragment : Fragment() {

    lateinit var apodViewData: ApodViewData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apod_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apodViewData = arguments?.getParcelable("apodViewData") as? ApodViewData ?: throw UnsupportedOperationException(
            "Apod Data required to show the details"
        )

        initView()
    }

    private fun initView() {
        ivApod.load(
            apodViewData.imageUrl,
            RequestOptions()
                .centerCrop()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_placeholder)
                .error(android.R.drawable.ic_menu_close_clear_cancel)
                .override(Target.SIZE_ORIGINAL)
                .dontTransform()
        )
    }
}
