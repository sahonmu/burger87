package com.sahonmu.burger87.viewmodels

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.sahonmu.burger87.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import data.sahonmu.burger87.repository.StoreRepositoryImpl
import domain.sahonmu.burger87.entity.StoreEntity
import domain.sahonmu.burger87.repository.StoreRepository
import domain.sahonmu.burger87.usecase.store.StoreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


data class MapViewModelUiState(
    var storeList: MutableList<StoreEntity> = mutableListOf(),
)

@HiltViewModel
class MapViewModel @Inject constructor(
//    private val storeUseCase: StoreUseCase,
    private val storeRepositoryImpl: StoreRepositoryImpl,
//    private val storeRepository: StoreRepository,
) : BaseViewModel() {

    private val _mapViewUiState = MutableStateFlow(MapViewModelUiState())
    val mapViewUiState = _mapViewUiState.asStateFlow()

    fun getStoreUseCase() {
        viewModelScope.launch {
//            storeUseCase().collect { store ->
//                Timber.i("2525 = $store")
//            }

            storeRepositoryImpl.getStore().collect { store ->
                Timber.i("25252525 = $store")
            }
        }
    }

    fun requestStoreList() {
//        val db = FirebaseFirestore.getInstance()
//
//        db.collection("root").addSnapshotListener { snapshot, e ->
//            if (e != null) {
//                return@addSnapshotListener
//            }
//
//            val storeList = mutableListOf<Store>()
//
//            snapshot?.documents?.forEach { item ->
//                val geoPoint = item.getGeoPoint("geoPoint")
//                geoPoint?.let { point ->
//                    val store = Store(
//                        id = item.get("id") as Long,
//                        name = item.get("name") as String,
//                        menu = item.get("menu") as List<String>,
//                        latitude = point.latitude,
//                        longitude = point.longitude,
//                        address = item.get("address") as String,
//                        branch = item.get("branch") as String,
//                        cityCode = item.get("cityCode") as String,
//                        cityName = item.get("cityName") as String,
//                        tel = item.get("tel") as String,
//                        score = item.get("score") as String,
//                        instagram = item.get("instagram") as String,
//                    )
//                    Timber.i("ITEM = ${store}")
//                    storeList.add(store)
//                }
//            }
//            _mapViewUiState.update { state ->
//                state.copy(
//                    storeList = storeList
//                )
//            }
//        }
    }

}