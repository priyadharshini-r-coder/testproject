package com.example.navigationcomponent.navigationmodules

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SelectionViewModel ( private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {

  /*  fun saveCoffeeTrackerSelection(enabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.saveCoffeeTrackerSelection(enabled)
        }
    }

    fun checkCoffeeTrackerEnabled(): LiveData<UserPreferencesRepository.Selection> {
        return userPreferencesRepository.coffeeTrackerPreferencesFlow.asLiveData()
    }*/
}

class SelectionViewModelFactory(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SelectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SelectionViewModel(userPreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}