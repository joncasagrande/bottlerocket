package com.joncasagrande.bottlerocket.ui.model

import com.joncasagrande.bottlerocket.R


private const val NO_CODE = -1

sealed class UiState<out T> {
    object Empty : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Display<out T>(val data: T) : UiState<T>()
    data class Error(val code: Int = NO_CODE, val message: Int = R.string.something_went_wrong, val stringMessage: String = "") : UiState<Nothing>()
}
