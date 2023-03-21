package com.sherlock.gb.kotlin.lessons.domain.room

import androidx.room.*
import com.sherlock.gb.kotlin.lessons.repository.City
import com.sherlock.gb.kotlin.lessons.repository.getDefaultCity

@Dao
interface HistoryDao {

    @Query("INSERT INTO history_table(city,temperature,feelsLike,icon) VALUES (:city,:temperature,:feelsLike,:icon)")
    fun nativeInsert(city: String, temperature: Int, feelsLike: Int, icon: String)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Query("SELECT * FROM history_table")
    fun getAll():List<HistoryEntity>

    @Query("SELECT * FROM history_table WHERE city=:city")
    fun getHistoryForCity(city: String):List<HistoryEntity>

}