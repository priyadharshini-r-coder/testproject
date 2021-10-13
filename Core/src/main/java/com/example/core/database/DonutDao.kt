package com.example.navigationcomponent.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.model.Donut


@Dao
interface DonutDao {
    @Query("SELECT * FROM donut")
    fun getAll(): LiveData<List<Donut>>

    @Query("SELECT * FROM donut WHERE id = :id")
    suspend fun get(id: Long): Donut

    @Insert
    suspend fun insert(donut: Donut): Long

    @Delete
    suspend fun delete(donut: Donut)

    @Update
    suspend fun update(donut: Donut)
}