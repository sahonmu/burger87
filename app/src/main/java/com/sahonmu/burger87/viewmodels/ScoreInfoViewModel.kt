package com.sahonmu.burger87.viewmodels

import androidx.lifecycle.viewModelScope
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.sahonmu.burger87.usecase.score.ScoreInfoUseCase
import domain.sahonmu.burger87.vo.score.ScoreInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ScoreInfoUiState(
    val loadState: LoadState = LoadState.LOADING,
    var scoreInfOList: MutableList<ScoreInfo> = mutableListOf(),
)



@HiltViewModel
class ScoreInfoViewModel @Inject constructor(
    private val scoreInfoUseCase: ScoreInfoUseCase
) : BaseViewModel() {

    private val _scoreInfoUiState = MutableStateFlow(ScoreInfoUiState())
    val scoreInfoUiState = _scoreInfoUiState.asStateFlow()

    fun requestScoreInfoList() {
        viewModelScope.launch {
            scoreInfoUseCase.invoke().collect { scoreInfoList ->
                _scoreInfoUiState.update { state ->
                    state.copy(
                        loadState = if (scoreInfoList.isEmpty()) LoadState.EMPTY else LoadState.FINISHED,
                        scoreInfOList = scoreInfoList.sortedByDescending { it.score } as MutableList<ScoreInfo>,
                    )
                }
            }
        }
    }


}