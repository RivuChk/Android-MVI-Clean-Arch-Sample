package dev.rivu.nasaapodarchive.apodlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import dev.rivu.nasaapodarchive.R
import dev.rivu.nasaapodarchive.domain.utils.format
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData
import dev.rivu.nasaapodarchive.utils.load
import kotlinx.android.synthetic.main.item_apod.view.*


class ApodListAdapter(val onClick: (Int,ApodViewData)->Unit) : RecyclerView.Adapter<ApodListAdapter.ApodViewHolder>() {

    private var apodItemList: List<ApodViewData> = listOf()

    fun showLoadingMore() {
        //TODO
    }

    fun hideLoadingMore() {
        //TODO
    }

    fun updateItems(apodItemList: List<ApodViewData>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(this.apodItemList, apodItemList))
        diffResult.dispatchUpdatesTo(this)
        this.apodItemList = apodItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        return ApodViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_apod, parent, false)
        )
    }

    override fun getItemCount(): Int = apodItemList.size

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        holder.bindView(apodItemList[position], position)
    }


    inner class ApodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(apodViewData: ApodViewData, position: Int) {
            itemView.setOnClickListener { view ->
                onClick(position, apodViewData)
            }
            ViewCompat.setTransitionName(itemView, apodViewData.date.format())
            itemView.ivApod.load(
                apodViewData.imageUrl,
                RequestOptions()
                    .centerCrop()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(android.R.drawable.ic_menu_close_clear_cancel)
            )
        }
    }
}