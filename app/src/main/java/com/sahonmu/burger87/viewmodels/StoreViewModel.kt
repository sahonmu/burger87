package com.sahonmu.burger87.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.sahonmu.burger87.enums.LoadState
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
import kotlin.collections.toList
import kotlin.collections.toMutableList


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
    var selectedTab: MutableState<StoreDetailTab> = mutableStateOf(StoreDetailTab.INFO)
)


data class StoreSortListUiState(
    var storeList: MutableList<Store> = mutableListOf(),
    var sortList: MutableList<Store> = mutableListOf(),
    var cityGroup: Map<String, List<Store>> = linkedMapOf(),
    var scoreGroup: Map<Float, List<Store>> = linkedMapOf(),
    var charGroup: Map<Char, List<Store>> = linkedMapOf(),
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

                val boundBuilder = LatLngBounds.builder()
                storeList.forEach { store ->
                    val point = LatLng(store.latitude, store.longitude)
                    boundBuilder.include(point)
                }

                _storeMapUiState.update { state ->
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
                cityGroup = storeList.groupBy { it.cityFilter }.toList().sortedByDescending { it.second.size }.toMap(),
                scoreGroup = scoreGroup,
                charGroup = storeList.groupBy { getChosung(it.name.first().uppercaseChar()) }.toList().sortedBy { it.first }.toMap(),
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
                cityGroup = list.groupBy { it.cityFilter }.toList().sortedByDescending { it.second.size }.toMap(),
                scoreGroup = scoreGroup,
                charGroup = list.groupBy { getChosung(it.name.first().uppercaseChar()) }.toList().sortedBy { it.first }.toMap(),
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
                searchList = storeList
            )
        }
    }
}