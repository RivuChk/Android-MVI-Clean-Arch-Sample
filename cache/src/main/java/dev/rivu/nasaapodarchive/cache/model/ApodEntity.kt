package dev.rivu.nasaapodarchive.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "apod")
data class ApodEntity (
    @PrimaryKey
    val date: String,
    @ColumnInfo(name = "explanation")
    val explanation: String,
    @ColumnInfo(name = "media_type")
    val mediaType: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "hd_url")
    val hdUrl: String? = null,
    @ColumnInfo(name = "copyright")
    val copyright: String? = null
)