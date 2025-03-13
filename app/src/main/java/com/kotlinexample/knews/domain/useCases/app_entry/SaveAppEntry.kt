package com.kotlinexample.knews.domain.useCases.app_entry

import com.kotlinexample.knews.domain.manager.LocalUsageManager

class SaveAppEntry (
    private val localUsageManager: LocalUsageManager
){

    suspend operator fun invoke() {
        localUsageManager.saveUserEntry()
    }

}