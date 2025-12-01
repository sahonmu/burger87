package com.sahonmu.burger87.viewmodels

import androidx.lifecycle.viewModelScope
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.sahonmu.burger87.usecase.store.StoreUseCase
import domain.sahonmu.burger87.vo.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MapViewModelUiState(
    val loadState: LoadState = LoadState.LOADING,
    var storeList: MutableList<Store> = mutableListOf(),
)

@HiltViewModel
class MapViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase,
) : BaseViewModel() {

    private val _mapViewUiState = MutableStateFlow(MapViewModelUiState())
    val mapViewUiState = _mapViewUiState.asStateFlow()

    fun requestStoreList() {
        viewModelScope.launch {
            storeUseCase.invoke().collect { storeList ->
                _mapViewUiState.update { state ->
                    state.copy(
                        loadState = if(storeList.isEmpty()) LoadState.EMPTY else LoadState.FINISHED,
                        storeList = storeList as MutableList<Store>
                    )
                }
            }
        }
    }
}