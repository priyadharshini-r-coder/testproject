package com.example.navigationcomponent.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navigationcomponent.database.DonutDao
import com.example.navigationcomponent.navigationmodules.UserPreferencesRepository
import com.example.navigationcomponent.viewmodel.DonutEntryViewModel
import com.example.navigationcomponent.viewmodel.DonutListViewModel

class DonutViewModelFactory   (private val donutDao: DonutDao,
                               private val preferencesRepository: UserPreferencesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DonutListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DonutListViewModel(donutDao, preferencesRepository) as T
        } else if (modelClass.isAssignableFrom(DonutEntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DonutEntryViewModel(donutDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}