package com.example.tbctaskk14.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbctaskk14.model.Data
import com.example.tbctaskk14.network.RetroObj
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _myFlow = MutableSharedFlow<Data>()
    val myFlow: SharedFlow<Data> get() = _myFlow


    fun info() {
        viewModelScope.launch {
            val response = RetroObj.getData().info()
            val body: Data? = response.body()
            if (response.isSuccessful && body != null) {
                _myFlow.emit(body)
            }
        }
    }
}