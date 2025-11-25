package com.sahonmu.burger87.viewmodels

import androidx.lifecycle.viewModelScope
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
//    private val keyGenLocalDataSource: KeyGenLocalDataSource,
) : BaseViewModel() {

    private val _toastData = MutableSharedFlow<String>()
    var toastData = _toastData.asSharedFlow()

    private val _restart = MutableSharedFlow<Unit>()
    var restart = _restart.asSharedFlow()

    private val _dismiss = MutableSharedFlow<Unit>()
    var dismiss = _dismiss.asSharedFlow()

    private val _pipMode = MutableStateFlow(Boolean)
    var pipMode = _pipMode.asStateFlow()

    fun showToast(message: String) {
        viewModelScope.launch {
            _toastData.emit(message)
        }
    }

    fun restart() {
        viewModelScope.launch { 
            _restart.emit(Unit)
        }
    }

    fun dismiss() {
        viewModelScope.launch {
            _dismiss.emit(Unit)
        }
    }
}