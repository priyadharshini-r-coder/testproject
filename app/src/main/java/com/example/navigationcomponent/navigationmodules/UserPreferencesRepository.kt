package com.example.navigationcomponent.navigationmodules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.prefs.Preferences


class UserPreferencesRepository private constructor(context: Context) {
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences> =
        context.createDataStore(name = "settings")

    object PreferencesKeys {
        val COFFEE_ENABLED = stringPreferencesKey("coffee_enabled")
    }

    enum class Selection {
        DONUT_ONLY, DONUT_AND_COFFEE, NOT_SELECTED
    }

    suspend fun saveCoffeeTrackerSelection(isSelected: Boolean) {

        val selection = when (isSelected) {
            true -> Selection.DONUT_AND_COFFEE.toString()
            false -> Selection.DONUT_ONLY.toString()
        }

        dataStore.edit { preferences ->
            preferences[PreferencesKeys.COFFEE_ENABLED] = selection
        }
    }

    val coffeeTrackerPreferencesFlow: Flow<Selection> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val strValue =
                preferences[PreferencesKeys.COFFEE_ENABLED] ?: Selection.NOT_SELECTED.toString()
            strValue.asEnumOrDefault()
        }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferencesRepository? = null

        fun getInstance(context: Context): UserPreferencesRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferencesRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }

    private inline fun <reified T : Enum<T>> String.asEnumOrDefault(): T =
        enumValues<T>().first { it.name.equals(this, ignoreCase = true) }
}