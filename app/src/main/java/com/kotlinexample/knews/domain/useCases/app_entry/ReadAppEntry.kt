package com.kotlinexample.knews.domain.useCases.app_entry

import com.kotlinexample.knews.domain.manager.LocalUsageManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private val localUsageManager: LocalUsageManager
    ){

    operator fun invoke() : Flow<Boolean> {
            return localUsageManager.readUserEntry()
        }

    }

