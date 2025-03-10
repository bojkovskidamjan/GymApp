package com.finki.gymapp.ui.legs

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.finki.gymapp.model.Workout
import com.finki.gymapp.repository.WorkoutRepository
import com.finki.gymapp.util.LEGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class LegsViewModel @ViewModelInject constructor(repository: WorkoutRepository) :
    ViewModel() {

    init {
        Timber.i("LegsViewModel init")
    }

    private val legsUiModelFlow: Flow<UiModel> = repository.getWorkouts().map { list ->
        val legs = list.filter { workout ->
            workout.category.contains(LEGS, true)
        }
        UiModel(legs)
    }

    val legsUiModel: LiveData<UiModel> = legsUiModelFlow.asLiveData()


    /**
     * Wraps the list of workouts that needs to be displayed in the UI.
     */
    data class UiModel(val workouts: List<Workout>)
}