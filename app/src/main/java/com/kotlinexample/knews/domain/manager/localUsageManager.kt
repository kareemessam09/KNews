package com.kotlinexample.knews.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUsageManager {

    suspend fun saveUserEntry()

    fun readUserEntry(): Flow<Boolean>

    suspend fun deleteUserEntry()


}