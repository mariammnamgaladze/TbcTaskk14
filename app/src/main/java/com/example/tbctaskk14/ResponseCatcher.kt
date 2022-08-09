package com.example.tbctaskk14

import com.example.tbctaskk14.model.Data

sealed class ResponseCatcher(val isLoading: Boolean) {
    class Success(val resultList: List<Data.Content>, isLoading: Boolean = false): ResponseCatcher(isLoading)
    class Error(val errorBody: String, isLoading: Boolean = false): ResponseCatcher(isLoading)
    class InProcess(isLoading: Boolean = true): ResponseCatcher(isLoading)
}