package com.gedehari.thethoughtmachine.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ThoughtDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(thought: Thought)

    @Query("SELECT * FROM thoughts WHERE id = :id")
    fun getThought(id: Int): Flow<Thought>

    @Query("SELECT * FROM thoughts ORDER BY id DESC")
    fun getThoughts(): Flow<List<Thought>>
}
