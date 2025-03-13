package com.kotlinexample.knews.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.kotlinexample.knews.domain.manager.LocalUsageManager
import com.kotlinexample.knews.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUsageManagerImpl (
    private val context: Context
): LocalUsageManager {


    override suspend fun saveUserEntry() {
        context.dataStore.edit { settings ->
            settings[appEntryPrefKey] = true
        }
    }

    override fun readUserEntry(): Flow<Boolean> {

        return context.dataStore.data.map { settings ->
            settings[appEntryPrefKey] ?: false
        }

    }

    override suspend fun deleteUserEntry() {

        context.dataStore.edit { settings ->
            settings[appEntryPrefKey] = false
        }

    }


    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

    private val appEntryPrefKey = booleanPreferencesKey(Constants.APP_ENTRY)



}