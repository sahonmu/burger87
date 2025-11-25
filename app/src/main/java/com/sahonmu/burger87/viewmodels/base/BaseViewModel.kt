package com.sahonmu.burger87.viewmodels.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProgressUiState(
    var show: Boolean = false
)

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {

    protected val _showProgressDialog = MutableStateFlow(ProgressUiState())
    var showProgressDialog = _showProgressDialog.asStateFlow()

    private val _exception = MutableSharedFlow<Throwable>()
    var exception = _exception.asSharedFlow()

    private val _toast = MutableSharedFlow<String>()
    var toast = _toast.asSharedFlow()

    private val _alert = MutableSharedFlow<String>()
    var alert = _alert.asSharedFlow()

    private val _logout = MutableSharedFlow<Unit>()
    var logout = _logout.asSharedFlow()

    protected val _popAlert = MutableSharedFlow<String>()
    var popAlert = _popAlert.asSharedFlow()

    fun toast(message: String) {
        viewModelScope.launch {
            _toast.emit(message)
        }
    }

    fun showAlert(message: String) {
        viewModelScope.launch {
            _alert.emit(message)
        }
    }

    fun logout() {
        viewModelScope.launch {
            _logout.emit(Unit)
        }
    }

    suspend fun apiException(throwable: Throwable) {
        _exception.emit(throwable)
//        if(throwable is retrofit2.HttpException) {
//            val response = throwable.response() as Response
//            Timber.i("apiException = $throwable = ${response.code()} / ${response.errorBody()}")
//            if(response.code() == ExceptionCode.SYSTEM_MAINTENANCE.code) {
//                _systemMaintenance.emit(Unit)
//                return
//            }
//
//            val errorResponse =
//                Gson().fromJson(response.errorBody()?.string(), ResponseApiException::class.java)
//            when (errorResponse.statusCode) {
//                ExceptionCode.JWT_ERROR.code -> {
//        //                val message = errorResponse.message
//        //                if(message is String) {
//        //                    _expiredToken.value = message
//        //                }
//                    _expiredToken.emit(Unit)
//                }
//                ExceptionCode.BAD.code -> {
//                    _exceptionData.emit(errorResponse.message.toString())
//                }
//                ExceptionCode.INTERNAL_SERVER_ERROR.code -> {
//                    _exceptionData.emit(ExceptionCode.INTERNAL_SERVER_ERROR.name)
//                }
//                ExceptionCode.APP_UPDATE.code -> {
//                    updateApp()
//                }
//                ExceptionCode.NOT_FOUND.code -> {
//                    _exceptionData.emit(ExceptionCode.NOT_FOUND.name)
//                }
//                else -> {
//                    _exceptionData.emit(errorResponse.description)
//                }
//            }
//        } else {
//            _exceptionData.emit("알수 없는 에러")
////            _exceptionData.emit(throwable.message!!)
//        }
    }

}
