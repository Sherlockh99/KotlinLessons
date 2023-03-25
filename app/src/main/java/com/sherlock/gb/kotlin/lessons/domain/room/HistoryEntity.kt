package com.sherlock.gb.kotlin.lessons.domain.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val city: String,
    /*val timestamp: Long, TODO HW Primary Key city + timestamp ???*/
    val temperature: Int,
    val feelsLike: Int,
    val icon: String,
    val condition: String = "cloudy")