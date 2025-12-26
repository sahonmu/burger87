package com.sahonmu.burger87.viewmodels

import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.sahonmu.burger87.vo.store.Store
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class StoreUiState(
    val loadState: LoadState = LoadState.LOADING,
    var originList: MutableList<Store> = mutableListOf(),
    var displayList: MutableList<Store> = mutableListOf(),
)

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val keyGenLocalDataSource: KeyGenLocalDataSource,
) : BaseViewModel() {

    private val _storeUiState = MutableStateFlow(StoreUiState())
    val storeUiState = _storeUiState.asStateFlow()


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

    fun clone(storeList: MutableList<Store>) {
        viewModelScope.launch {
            _storeUiState.update { state ->
                state.copy(
                    loadState = if (storeList.isEmpty()) LoadState.EMPTY else LoadState.FINISHED,
                    originList = storeList.sortedBy { it.id }.toMutableList(),
                    displayList = storeList.sortedBy { it.id }.toMutableList(),
                )
            }
        }
    }
}