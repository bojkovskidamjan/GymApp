package com.finki.gymapp.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.room.Room
import com.finki.gymapp.db.GymificDatabase
import com.finki.gymapp.util.DATABASE_NAME
import com.finki.gymapp.util.PREFERENCE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): GymificDatabase {
        return Room
            .databaseBuilder(app, GymificDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWorkoutDao(db: GymificDatabase) = db.workoutDao

    @Provides
    @Singleton
    fun provideWorkoutCategoryDao(db: GymificDatabase) = db.categoryDao

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.createDataStore(name = PREFERENCE_NAME)
}