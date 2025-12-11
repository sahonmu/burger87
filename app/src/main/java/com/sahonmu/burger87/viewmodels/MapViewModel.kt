package com.sahonmu.burger87.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.sahonmu.burger87.common.DataManager
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.enums.StoreDetailTab
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.sahonmu.burger87.enums.StoreState
import domain.sahonmu.burger87.enums.isOperation
import domain.sahonmu.burger87.usecase.store.StoreUseCase
import domain.sahonmu.burger87.vo.score.ScoreInfo
import domain.sahonmu.burger87.vo.store.Store
import domain.sahonmu.burger87.vo.store.StoreImage
import domain.sahonmu.burger87.vo.store.StoreMenu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.collections.toMutableList


data class MapViewModelUiState(
    val loadState: LoadState = LoadState.LOADING,
    var originList: MutableList<Store> = mutableListOf(),
    var storeList: MutableList<Store> = mutableListOf(),
    var boundBuilder: LatLngBounds.Builder = LatLngBounds.builder(),
    var selectedIndex: MutableState<Int> = mutableStateOf(0)
)


data class StoreDetailUiState(
    val loadState: LoadState = LoadState.LOADING,
    var storeImageLst: MutableList<StoreImage> = mutableListOf(),
    var storeMenuList: MutableList<StoreMenu> = mutableListOf(),
    var selectedTab: MutableState<StoreDetailTab> = mutableStateOf(StoreDetailTab.INFO)
)


data class StoreSortListUiState(
    var storeList: MutableList<Store> = mutableListOf(),
    var sortList: MutableList<Store> = mutableListOf(),
    var cityGroup: Map<String, List<Store>> = linkedMapOf(),
    var scoreGroup: Map<Float, MutableList<Store>> = linkedMapOf(),
    var charGroup: Map<Char, List<Store>> = linkedMapOf(),
    var includeClosed: Boolean = true
)

@HiltViewModel
class MapViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase,
) : BaseViewModel() {

    private val _mapViewUiState = MutableStateFlow(MapViewModelUiState())
    val mapViewUiState = _mapViewUiState.asStateFlow()

    private val _storeDetailUiState = MutableStateFlow(StoreDetailUiState())
    val storeDetailUiState = _storeDetailUiState.asStateFlow()


    private val _storeListUiState = MutableStateFlow(StoreSortListUiState())
    val storeListUiState = _storeListUiState.asStateFlow()

    fun requestStoreList() {
        viewModelScope.launch {
            storeUseCase.invoke().collect { storeList ->

                val boundBuilder = LatLngBounds.builder()
                storeList.forEach { store ->
                    val point = LatLng(store.latitude, store.longitude)
                    boundBuilder.include(point)
                }

                _mapViewUiState.update { state ->
                    state.copy(
                        loadState = if (storeList.isEmpty()) LoadState.EMPTY else LoadState.FINISHED,
                        originList = storeList.sortedBy { it.id } as MutableList<Store>,
                        storeList = storeList.sortedByDescending { it.storeState.isOperation() } as MutableList<Store>,
                        boundBuilder = boundBuilder
                    )
                }
            }
        }
    }

    fun requestStoreImageList(id: Long) {
        viewModelScope.launch {
            storeUseCase.invoke(id).collect { list ->
                _storeDetailUiState.update { state ->
                    state.copy(
                        loadState = if (list.isEmpty()) LoadState.EMPTY else LoadState.FINISHED,
                        storeImageLst = if (list.isEmpty()) list as MutableList<StoreImage> else list.sortedBy { it.id } as MutableList<StoreImage>
                    )
                }
            }
        }
    }

    fun requestStoreMenuList(id: Long) {
        viewModelScope.launch {
            Timber.i("list ==== ${storeUseCase.getStoreMenu(id)}")
            storeUseCase.getStoreMenu(id).collect { list ->
                _storeDetailUiState.update { state ->
                    state.copy(
                        storeMenuList = if (list.isEmpty()) list as MutableList<StoreMenu> else list.sortedBy { it.id } as MutableList<StoreMenu>
                    )
                }
            }
        }
    }

    fun addAllStore(storeList: MutableList<Store>) {
        _storeListUiState.update { state ->
            val scoreGroup = storeList.groupBy { it.score }.toSortedMap( compareByDescending { it })
            state.copy(
                storeList = storeList,
                sortList = storeList,
                cityGroup = storeList.groupBy { it.cityFilter },
                scoreGroup = scoreGroup as Map<Float, MutableList<Store>>,
//                flatScoreList = scoreGroup.flatMap { (key, list) ->
//                    list.map { item -> key to item }
//                } as MutableList<Store>,
                charGroup = storeList.groupBy { getChosung(it.name.first().uppercaseChar()) }
            )
        }
    }

    fun sortStore(storeList: MutableList<Store>) {
        _storeListUiState.update { state ->
            state.copy(
                sortList = storeList
            )
        }
    }

    fun sortDefault(selectedCity: String) {
        _storeListUiState.update { state ->
            state.copy(
                sortList = if (selectedCity == "전체") state.storeList else state.storeList.filter { it.cityFilter == selectedCity }
                    .toMutableList()
            )
        }
    }

    fun sortAbc() {
        _storeListUiState.update { state ->
            state.copy(
                sortList = state.sortList.sortedBy { it.name }.toMutableList()
            )
        }
    }

    fun sortScore() {
        _storeListUiState.update { state ->
            state.copy(
                sortList = state.sortList.sortedByDescending { it.score }.toMutableList()
            )
        }
    }

    fun includeClosedStore(include: Boolean) {
        _storeListUiState.update { state ->
            var list = state.storeList
            if(!include) {
                list = list.filterNot { it.storeState == StoreState.CLOSED }.toMutableList()
            }

            val scoreGroup = list.groupBy { it.score }.toSortedMap( compareByDescending { it })

            state.copy(
                sortList = list,
                includeClosed = include,
                cityGroup = list.groupBy { it.cityFilter },
                scoreGroup = scoreGroup as Map<Float, MutableList<Store>>,
//                flatScoreList = scoreGroup.flatMap { (key, list) ->
//                    list.map { item -> key to item }
//                } as MutableList<Store>,
//                flatScoreList = list.groupBy { it.score }.toList().sortedBy { it.first }.flatMap { (header, items) ->
//                    listOf(header) + items
//                } as MutableList<Store>,
                charGroup = list.groupBy { getChosung(it.name.first().uppercaseChar()) }
            )
        }
    }

    fun getChosung(c: Char): Char {
        val base = 0xAC00
        val last = 0xD7A3
        if (c.code < base || c.code > last) return c

        val index = (c.code - base) / (21 * 28)
        return "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ"[index]
    }
}