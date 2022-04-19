package com.mylibrary.bookapp.db

import androidx.room.*
import com.mylibrary.bookapp.db.tables.EventEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM event ORDER BY id DESC")
    fun getMyEvents(): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(eventEntity: EventEntity)

    @Delete
    fun deleteEvent(eventEntity: EventEntity)

    @Query("DELETE FROM event")
    fun deleteAll()

    @Query("DELETE FROM event where id = :eventId")
    fun deleteEventById(eventId: Int)
}