package com.sahonmu.burger87.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.enums.SortMenu
import com.sahonmu.burger87.enums.StoreDetailTab
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.sahonmu.burger87.enums.StoreState
import domain.sahonmu.burger87.enums.isOperation
import domain.sahonmu.burger87.usecase.store.StoreUseCase
import domain.sahonmu.burger87.vo.store.Store
import domain.sahonmu.burger87.vo.store.StoreImage
import domain.sahonmu.burger87.vo.store.StoreMenu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.collections.Map
import kotlin.collections.toList
import kotlin.collections.toMutableList
import kotlin.collections.toSortedMap


data class StoreMapUiState(
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
    var selectedTab: MutableState<StoreDetailTab> = mutableStateOf(StoreDetailTab.INFO),
    var store: Store? = null
)


data class StoreSortListUiState(
    var storeList: MutableList<Store> = mutableListOf(),
    var displayList: MutableList<Store> = mutableListOf(),
    var cityGroup: Map<String, List<Store>> = linkedMapOf(),
    var scoreGroup: Map<String, List<Store>> = linkedMapOf(),
    var charGroup: Map<String, List<Store>> = linkedMapOf(),
    var visitCountGroup: Map<String, List<Store>> = linkedMapOf(),
    var filterGroup: Map<String, List<Store>> = linkedMapOf(),
    var selectedFilterMenu: String = "",
    var includeClosed: Boolean = true,
)


data class StoreSearchUiState(
    var includeClosed: Boolean = true,
    var searchList: MutableList<Store> = mutableListOf(),
)

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val storeUseCase: StoreUseCase,
) : BaseViewModel() {

    private val _storeMapUiState = MutableStateFlow(StoreMapUiState())
    val storeMapUiState = _storeMapUiState.asStateFlow()

    private val _storeDetailUiState = MutableStateFlow(StoreDetailUiState())
    val storeDetailUiState = _storeDetailUiState.asStateFlow()


    private val _storeListUiState = MutableStateFlow(StoreSortListUiState())
    val storeListUiState = _storeListUiState.asStateFlow()


    private val _storeSearchUiState = MutableStateFlow(StoreSearchUiState())
    val storeSearchUiState = _storeSearchUiState.asStateFlow()

    fun requestStoreList() {
        viewModelScope.launch {
            storeUseCase.invoke().collect { storeList ->
                Timber.i("storeList = ${storeList.size}")
                val boundBuilder = LatLngBounds.builder()
                storeList.forEach { store ->
                    val point = LatLng(store.latitude, store.longitude)
                    boundBuilder.include(point)
                }
                _storeMapUiState.update { state ->
                    state.copy(
                        loadState = if (storeList.isEmpty()) LoadState.EMPTY else LoadState.FINISHED,
                        originList = storeList.sortedBy { it.id }.toMutableList(),
//                        storeList = storeList.sortedByDescending { it.storeState.isOperation() } as MutableList<Store>,
                        storeList = storeList.sortedByDescending { it.id }.toMutableList(),
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
            storeUseCase.storeMenuList(id).collect { list ->
                _storeDetailUiState.update { state ->
                    state.copy(
                        storeMenuList = if (list.isEmpty()) list as MutableList<StoreMenu> else list.sortedBy { it.id } as MutableList<StoreMenu>
                    )
                }
            }
        }
    }


    fun requestStoreDetailList(id: Long) {
//        requestStoreMenuList(id)
//        requestStoreImageList(id)
        viewModelScope.launch {
            storeUseCase.storeDetail(id).collect { store ->
                _storeDetailUiState.update { state ->
                    state.copy(
                        store = store
                    )
                }
            }
        }
    }

    fun addAllStore(storeList: MutableList<Store>) {
        _storeListUiState.update { state ->
            val list = storeList.sortedBy { it.id }.sortedByDescending { it.storeState.isOperation() }.toMutableList()
//            val list = state.storeList.sortedBy { it.id }
            val scoreGroup = list.groupBy { it.score.toString() }.toSortedMap( compareByDescending { it })
            state.copy(
                storeList = list,
                displayList = list,
                cityGroup = list.groupBy { it.cityFilter }.toList().sortedByDescending { it.second.size }.toMap(),
                scoreGroup = scoreGroup,
                charGroup = list.groupBy { getChosung(it.name.first().uppercaseChar()).toString() }.toList().sortedBy { it.first }.toMap(),
                visitCountGroup = list.groupBy { it.visitCount.toString() }.toList().sortedByDescending { it.first.toInt() }.toMap(),
                filterGroup = scoreGroup
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

    fun searchByKeyword(keyword: String, storeList: MutableList<Store>) {
        val searchList = storeList.filter { it.fullName.contains(keyword, ignoreCase = true) }
        _storeSearchUiState.update { state ->
            state.copy(
                searchList = searchList.toMutableList(),
            )
        }
    }

    fun searchByReset(storeList: MutableList<Store>) {
        _storeSearchUiState.update { state ->
            state.copy(
                searchList = storeList.sortedBy { it.name }.toMutableList()
            )
        }
    }

    fun filterCity(city: String, includeClosed: Boolean = true) {
        val list = if(includeClosed) storeListUiState.value.storeList else storeListUiState.value.storeList.filter { it.storeState.isOperation() }
        _storeListUiState.update { state ->
            state.copy(
                displayList = list.filter { it.cityFilter == city }.toMutableList(),
                selectedFilterMenu = city
            )
        }
    }

    fun filterScore(score: String) {
        score.toFloatOrNull()?.let {
            _storeListUiState.update { state ->
                state.copy(
                    displayList = state.storeList.filter { it.score == score.toFloat() }.toMutableList()
                )
            }
        }
    }

    fun filterChar(char: String) {
        _storeListUiState.update { state ->
            state.copy(
                displayList = state.storeList.filter { getChosung(it.name.first().uppercaseChar()).toString() == char }.toMutableList(),
            )
        }
    }

    fun filterVisitCount(visitCount: String) {
        visitCount.toIntOrNull()?.let {
            _storeListUiState.update { state ->
                state.copy(
                    displayList = state.storeList.filter { it.visitCount == visitCount.toInt() }.toMutableList()
                )
            }
        }
    }

    fun filterReset() {
        _storeListUiState.update { state ->
            state.copy(
                displayList = state.storeList
            )
        }
    }

    fun filterList(selectedSortMenu: SortMenu) {
        _storeListUiState.update { state ->
            var filterMenu: String
            val filterGroup = when(selectedSortMenu) {
                SortMenu.CITY -> {
                    filterMenu = state.cityGroup.maxBy { it.value.size }.key
                    filterCity(filterMenu)
                    state.cityGroup
                } SortMenu.SCORE -> {
                    filterMenu = state.scoreGroup.maxBy { it.key }.key
                    filterScore(filterMenu)
                    state.scoreGroup
                } SortMenu.CHAR -> {
                    filterMenu = state.charGroup.keys.first()
                    filterChar(filterMenu)
                    state.charGroup
                } SortMenu.VISIT_COUNT -> {
                    filterMenu = state.visitCountGroup.keys.first()
                    filterVisitCount(filterMenu)
                    state.visitCountGroup
                } else -> {
                    filterMenu = ""
                    linkedMapOf()
                }
            }

            state.copy(
                filterGroup = filterGroup,
                selectedFilterMenu = filterMenu
            )
        }
    }
}