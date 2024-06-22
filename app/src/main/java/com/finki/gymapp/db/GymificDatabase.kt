package com.finki.gymapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.finki.gymapp.model.Workout
import com.finki.gymapp.model.WorkoutCategory

@Database(entities = [Workout::class, WorkoutCategory::class], version = 1)
abstract class GymificDatabase : RoomDatabase() {
    abstract val workoutDao: WorkoutDao
    abstract val categoryDao: WorkoutCategoryDao
}