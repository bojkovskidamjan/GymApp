package com.finki.gymapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.finki.gymapp.model.WorkoutCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutCategoryDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<WorkoutCategory>)

    @Query("SELECT * FROM category_table")
    fun getCategories(): Flow<List<WorkoutCategory>>
}