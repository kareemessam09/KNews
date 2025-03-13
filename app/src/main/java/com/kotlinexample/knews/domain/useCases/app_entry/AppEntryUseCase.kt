package com.kotlinexample.knews.domain.useCases.app_entry

data class AppEntryUseCase (
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry,
    val deleteAppEntry: DeleteAppEntry
)