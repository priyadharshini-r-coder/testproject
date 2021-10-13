package com.example.coffee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coffee.model.CoffeeDao

class CoffeeViewModelfactory (private val coffeeDao: CoffeeDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoffeeListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CoffeeListViewModel(coffeeDao) as T
        } else if (modelClass.isAssignableFrom(CoffeeEntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CoffeeEntryViewModel(coffeeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}