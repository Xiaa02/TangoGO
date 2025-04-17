package com.example.tangogo.ui.screens.activitylog

import com.example.tangogo.common.ext.fromDateToLong
import com.example.tangogo.common.ext.toDateTimeString
import com.example.tangogo.model.Activity

data class AddActivityUiState(
    val id: String = "",
    val name: String = "",
    val duration: String = "",
    val datetime: Long = 0L
)

fun AddActivityUiState.toActivity(): Activity = Activity(
    id = id,
    name = name,
    duration = duration.toInt(),
    datetime = datetime.toDateTimeString()
)

fun Activity.toAddActivityUiState(): AddActivityUiState = AddActivityUiState(
    id = id,
    name = name,
    duration = duration.toString(),
    datetime = datetime.fromDateToLong()
)