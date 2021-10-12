package com.example.navigationcomponent.dependencyinjection.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.navigationcomponent.dependencyinjection.data.model.User
import com.example.navigationcomponent.dependencyinjection.data.repository.MainRepository
import com.example.navigationcomponent.dependencyinjection.utils.NetworkHelper
import com.example.navigationcomponent.dependencyinjection.utils.Resource

import kotlinx.coroutines.launch

class DependencyViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getUsers().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }
}