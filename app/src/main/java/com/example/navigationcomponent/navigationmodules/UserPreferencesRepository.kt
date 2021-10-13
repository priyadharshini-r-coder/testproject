package com.example.navigationcomponent.navigationmodules

import android.content.Context
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import java.util.prefs.Preferences


class UserPreferencesRepository private constructor(context: Context) {
   /* private val dataStore: DataStore<Preferences>

        context.createDataStore(name = "settings")
*/
  /*  object PreferencesKeys {
        val COFFEE_ENABLED = stringPreferencesKey("coffee_enabled")
    }*/

    enum class Selection {
        DONUT_ONLY, DONUT_AND_COFFEE, NOT_SELECTED
    }

    suspend fun saveCoffeeTrackerSelection(isSelected: Boolean) {

        val selection = when (isSelected) {
            true -> Selection.DONUT_AND_COFFEE.toString()
            false -> Selection.DONUT_ONLY.toString()
        }

        /*dataStore.edit { preferences ->
            preferences[PreferencesKeys.COFFEE_ENABLED] = selection
        }*/
    }

   /* val coffeeTrackerPreferencesFlow: Flow<Selection> = dataStore.data
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
*/
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