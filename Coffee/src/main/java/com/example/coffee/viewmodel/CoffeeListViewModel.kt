package com.example.coffee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Coffee
import com.example.coffee.model.CoffeeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeListViewModel (private val coffeeDao: CoffeeDao) : ViewModel() {

    // Users of this ViewModel will observe changes to its coffees list to know when
    // to redisplay those changes
    val coffeeList: LiveData<List<Coffee>> = coffeeDao.getAll()

    fun delete(coffee: Coffee) = viewModelScope.launch(Dispatchers.IO) {
        coffeeDao.delete(coffee)
    }
}