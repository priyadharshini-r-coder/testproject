package com.example.coffee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.core.model.Coffee
import com.example.coffee.model.CoffeeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeEntryViewModel (private val coffeeDao: CoffeeDao) : ViewModel() {

    private var coffeeLiveData: LiveData<Coffee>? = null

    fun get(id: Long): LiveData<Coffee> {
        return coffeeLiveData ?: liveData {
            emit(coffeeDao.get(id))
        }.also {
            coffeeLiveData = it
        }
    }

    fun addData(
        id: Long,
        name: String,
        description: String,
        rating: Int,
        setupNotification: (Long) -> Unit
    ) {
        val coffee = Coffee(id, name, description, rating)

        CoroutineScope(Dispatchers.IO).launch {
            var actualId = id

            if (id > 0) {
                update(coffee)
            } else {
                actualId = insert(coffee)
            }

            setupNotification(actualId)
        }
    }

    private suspend fun insert(donut: Coffee) = coffeeDao.insert(donut)

    private fun update(donut: Coffee) = viewModelScope.launch(Dispatchers.IO) {
        coffeeDao.update(donut)
    }
}