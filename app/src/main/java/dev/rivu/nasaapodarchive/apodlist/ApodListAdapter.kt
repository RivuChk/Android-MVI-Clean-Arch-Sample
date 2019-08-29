package dev.rivu.nasaapodarchive.apodlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.rivu.nasaapodarchive.R
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData
import kotlinx.android.synthetic.main.item_apod.view.*
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.DiffUtil


class ApodListAdapter : RecyclerView.Adapter<ApodListAdapter.ApodViewHolder>() {

    private val apodItemList: List<ApodViewData> = listOf()

    fun updateItems(apodItemList: List<ApodViewData>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(this.apodItemList, apodItemList))
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        return ApodViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_apod, parent, false)
        )
    }

    override fun getItemCount(): Int = apodItemList.size

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        holder.itemView.tvDescription.text = apodItemList[position].explanation
    }


    class ApodViewHolder(view: View) : RecyclerView.ViewHolder(view)
}