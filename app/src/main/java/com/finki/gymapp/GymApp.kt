package com.finki.gymapp

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.finki.gymapp.worker.PopulateCategoryTableWorker
import com.finki.gymapp.worker.PopulateWorkoutTableWorker
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class GymApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        plantTimber()
        prepopulateDatabase()
    }

    private fun plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    //Pre-populate database with raw data.
    private fun prepopulateDatabase() {
        val populateWorkoutTable = OneTimeWorkRequestBuilder<PopulateWorkoutTableWorker>().build()
        val populateCategoryTable = OneTimeWorkRequestBuilder<PopulateCategoryTableWorker>().build()

        WorkManager.getInstance(applicationContext)
            .beginWith(populateWorkoutTable)
            .then(populateCategoryTable)
            .enqueue()
    }

    //To inject WorkManager object by Hilt.
    override fun getWorkManagerConfiguration() =
        Configuration.Builder().setWorkerFactory(workerFactory).build()
}