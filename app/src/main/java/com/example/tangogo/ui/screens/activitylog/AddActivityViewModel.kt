package com.example.tangogo.ui.screens.activitylog

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.tangogo.common.ext.idFromParameter
import com.example.tangogo.model.Activity
import com.example.tangogo.model.service.LogService
import com.example.tangogo.model.service.StorageService
import com.example.tangogo.ui.screens.TangoGOViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddActivityViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService: LogService,
    private val storageService: StorageService
) : TangoGOViewModel(logService) {
    var uiState = mutableStateOf(AddActivityUiState())
        private set

    init {
        val activityId = savedStateHandle.get<String>(ACTIVITY_ID)
        if (activityId != null) {
            launchCatching {
                val activity =
                    storageService.getActivity(activityId.idFromParameter()) ?: Activity()
                uiState.value = activity.toAddActivityUiState()
            }
        }
    }

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue)
    }

    fun onDurationChange(newValue: String) {
        uiState.value = uiState.value.copy(duration = newValue)
    }

    fun onDateTimeChange(newValue: Long) {
        uiState.value = uiState.value.copy(datetime = newValue)
    }

    fun onAddClick(popUpScreen: () -> Unit) {
        launchCatching {
            val editedActivity = uiState.value.toActivity()
            if (editedActivity.id.isBlank()) {
                storageService.saveActivity(editedActivity)
            } else {
                storageService.updateActivity(editedActivity)
            }
            popUpScreen()
        }
    }

    fun onBackClick(popUp: () -> Unit) = popUp()

    private fun Int.toClockPattern(): String {
        return if (this < 10) "0$this" else "$this"
    }

    companion object {
        private const val ACTIVITY_ID = "activityId"
        private const val UTC = "UTC"
        private const val DATE_FORMAT = "EEE, d MMM yyyy"
    }
}