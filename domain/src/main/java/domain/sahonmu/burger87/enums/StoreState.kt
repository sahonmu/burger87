package domain.sahonmu.burger87.enums

enum class StoreState {
    OPERATION,
    CLOSED
}

fun String.storeState(): StoreState {
    return when(this) {
        StoreState.OPERATION.toString() -> StoreState.OPERATION
        else -> StoreState.CLOSED
    }
}