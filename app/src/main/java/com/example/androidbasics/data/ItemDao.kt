package com.example.androidbasics.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM item WHERE id = :id")
    fun getById(id: Int): Flow<Item>

    @Query("SELECT * FROM item ORDER BY name ASC")
    fun getAll(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)
}