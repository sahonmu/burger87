package com.sahonmu.burger87.viewmodels

import androidx.lifecycle.viewModelScope
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.sahonmu.burger87.usecase.announcement.AnnouncementUseCase
import domain.sahonmu.burger87.vo.announcement.Announcement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AnnouncementListUiState(
    var announcementList: MutableList<Announcement> = mutableListOf(),
    var headerAnnouncementList: MutableList<Announcement> = mutableListOf(),
)



@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val announcementUseCase: AnnouncementUseCase
) : BaseViewModel() {

    private val _announcementListUiState = MutableStateFlow(AnnouncementListUiState())
    val announcementListUiState = _announcementListUiState.asStateFlow()

    fun requestAnnouncementList() {
        viewModelScope.launch {
            announcementUseCase.invoke().collect { announcementList ->
                _announcementListUiState.update { state ->
                    state.copy(
                        announcementList = announcementList.filter { !it.isHeader } .sortedBy { it.id }.toMutableList(),
                        headerAnnouncementList = announcementList.filter { it.isHeader } .sortedBy { it.id }.toMutableList(),
                    )
                }
            }
        }
    }

}