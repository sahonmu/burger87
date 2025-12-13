package com.sahonmu.burger87.viewmodels

import androidx.lifecycle.viewModelScope
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.sahonmu.burger87.usecase.event.EventUseCase
import domain.sahonmu.burger87.vo.app.AppInfo
import domain.sahonmu.burger87.vo.event.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class EventUiState(
    val loadState: LoadState = LoadState.LOADING,
    var eventList: MutableList<Event> = mutableListOf()
)

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventUseCase: EventUseCase
) : BaseViewModel() {

    private val _eventUiState = MutableStateFlow(EventUiState())
    val eventUiState = _eventUiState.asStateFlow()


    fun requestEventList() {
        viewModelScope.launch {
            eventUseCase.invoke().collect { eventList ->
                _eventUiState.update { state ->
                    state.copy(
                        loadState = if(eventList.isEmpty()) LoadState.EMPTY else LoadState.FINISHED,
                        eventList = eventList.toMutableList()
                    )
                }
            }
        }
    }
}