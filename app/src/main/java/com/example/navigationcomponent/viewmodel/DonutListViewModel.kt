package com.example.navigationcomponent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationcomponent.database.DonutDao
import com.example.navigationcomponent.model.Donut
import com.example.navigationcomponent.navigationmodules.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DonutListViewModel  (private val donutDao: DonutDao,
                           private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    // Users of this ViewModel will observe changes to its donuts list to know when
    // to redisplay those changes
    val donuts: LiveData<List<Donut>> = donutDao.getAll()

    fun delete(donut: Donut) = viewModelScope.launch(Dispatchers.IO) {
        donutDao.delete(donut)
    }

    fun isFirstRun(): LiveData<UserPreferencesRepository.Selection> {
        return userPreferencesRepository.coffeeTrackerPreferencesFlow.asLiveData()
    }
}