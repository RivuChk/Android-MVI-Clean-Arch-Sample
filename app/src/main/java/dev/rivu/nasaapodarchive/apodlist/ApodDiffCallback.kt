package dev.rivu.nasaapodarchive.apodlist

import androidx.recyclerview.widget.DiffUtil
import dev.rivu.nasaapodarchive.domain.utils.format
import dev.rivu.nasaapodarchive.presentation.apodlist.model.ApodViewData


class MyDiffCallback(private val newList: List<ApodViewData>, private val oldList: List<ApodViewData>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val equality = oldList[oldItemPosition].date.format().equals(newList[newItemPosition].date.format(), ignoreCase = true)
        return equality
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true//if items are same, contents are same
    }
}