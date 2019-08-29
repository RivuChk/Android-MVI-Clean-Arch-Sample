package dev.rivu.nasaapodarchive.apodlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import dev.rivu.nasaapodarchive.R
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData
import kotlinx.android.synthetic.main.item_apod.view.*


class ApodListAdapter : RecyclerView.Adapter<ApodListAdapter.ApodViewHolder>() {

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
        holder.bindView(apodItemList[position])
    }


    class ApodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(apodViewData: ApodViewData) {
            Glide.with(itemView)
                .load(apodViewData.imageUrl)
                .apply(RequestOptions()
                    .centerCrop()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(android.R.drawable.ic_menu_close_clear_cancel))
                .into(itemView.ivApod)
        }
    }
}