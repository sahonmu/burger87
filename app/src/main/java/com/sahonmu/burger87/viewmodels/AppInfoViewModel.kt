package com.sahonmu.burger87.viewmodels

import androidx.lifecycle.viewModelScope
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.sahonmu.burger87.usecase.app.AppInfoUseCase
import domain.sahonmu.burger87.usecase.store.StoreUseCase
import domain.sahonmu.burger87.vo.app.AppInfo
import domain.sahonmu.burger87.vo.store.Store
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


data class AppInfoViewModelUiState(
    val loadState: LoadState = LoadState.LOADING,
    var appInfo: AppInfo = AppInfo(
        id = 0,
        os = "AOS",
        forceUpdate = false,
        appVersionCode = 0,
        appVersion = ""
    ),
)

@HiltViewModel
class AppInfoViewModel @Inject constructor(
    private val appInfoUseCase: AppInfoUseCase,
) : BaseViewModel() {

    private val _appInfoViewUiState = MutableStateFlow(AppInfoViewModelUiState())
    val appInfoViewUiState = _appInfoViewUiState.asStateFlow()

    private val _forceUpdateData = MutableSharedFlow<Boolean>()
    var forceUpdateData = _forceUpdateData.asSharedFlow()

    fun requestAppInfo() {
        viewModelScope.launch {
            appInfoUseCase.invoke(os = "AOS").collect { appInfoList ->
                val appInfo = appInfoList.last()
                _appInfoViewUiState.update { state ->
                    state.copy(
                        loadState = if(appInfoList.isEmpty()) LoadState.EMPTY else LoadState.FINISHED,
                        appInfo = state.appInfo.copy(
                            id = appInfo.id,
                            os = appInfo.os,
                            appVersion = appInfo.appVersion,
                            forceUpdate = appInfo.forceUpdate,
                            appVersionCode = appInfo.appVersionCode,
                        )
                    )
                }
            }
        }
    }

    fun checkForceUpdate(versionCode: Long) {
        viewModelScope.launch {
            appInfoUseCase.invoke(os = "AOS").collect { appInfoList ->
                if(appInfoList.size == 1) {
                    _forceUpdateData.emit(false)
                    return@collect
                }
                val infoList = appInfoList.filter {  it.appVersionCode > versionCode }
                val exists = infoList.find { it.forceUpdate } != null
                _forceUpdateData.emit(exists)
            }
        }

    }
}