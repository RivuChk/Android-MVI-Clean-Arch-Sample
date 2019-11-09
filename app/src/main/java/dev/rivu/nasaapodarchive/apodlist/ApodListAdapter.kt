package dev.rivu.nasaapodarchive.apodlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding3.view.clicks
import dev.rivu.nasaapodarchive.R
import dev.rivu.nasaapodarchive.domain.utils.format
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData
import dev.rivu.nasaapodarchive.utils.load
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_apod.view.*

const val APOD_ITEM = 1
const val LOAD_MORE_ITEM = 2

class ApodListAdapter : RecyclerView.Adapter<ApodListAdapter.ApodViewHolder>() {

    var apodItemList: List<ApodViewData> = listOf()
        private set

    private val clickObservable: PublishSubject<ApodClickData> = PublishSubject.create()

    var isShowingLoadingMore: Boolean = false

    fun showLoadingMore() {
        if (!isShowingLoadingMore) {
            isShowingLoadingMore = true
            notifyItemInserted(apodItemList.size)
        }
    }

    fun hideLoadingMore() {
        if (isShowingLoadingMore) {
            isShowingLoadingMore = false
            notifyItemRemoved(apodItemList.size)
        }
    }

    val clickEvent: Observable<ApodClickData> = clickObservable.hide()

    fun updateItems(apodItemList: List<ApodViewData>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(apodItemList, this.apodItemList))
        diffResult.dispatchUpdatesTo(this)
        this.apodItemList = apodItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        return if (viewType == APOD_ITEM) {
            ApodViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_apod, parent, false),
                viewType
            )
        } else {
            ApodViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_load_more, parent, false),
                viewType
            )
        }
    }

    override fun getItemCount(): Int =
        if (isShowingLoadingMore) apodItemList.size + 1 else apodItemList.size

    override fun getItemViewType(position: Int): Int =
        if (position >= apodItemList.size && isShowingLoadingMore) LOAD_MORE_ITEM else APOD_ITEM

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        if (holder.viewType == APOD_ITEM) {
            holder.bindView(apodItemList[position], position)
        } else {
            holder.bindLoadMoreItem()
        }
    }

    inner class ApodViewHolder(view: View, val viewType: Int) : RecyclerView.ViewHolder(view) {

        fun bindView(apodViewData: ApodViewData, position: Int) {

            itemView.clicks()
                .map {
                    ApodClickData(
                        position, apodViewData
                    )
                }
                .subscribe(clickObservable)

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

        fun bindLoadMoreItem() {

        }
    }

    data class ApodClickData(
        val position: Int,
        val apodViewData: ApodViewData
    )
}