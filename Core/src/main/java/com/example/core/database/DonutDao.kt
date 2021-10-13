package com.example.navigationcomponent.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

import com.example.navigationcomponent.model.Donut

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