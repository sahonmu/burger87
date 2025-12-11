package domain.sahonmu.burger87.enums

enum class StoreState(val state: String) {
    OPERATION("operation"),
    CLOSED("close")
}

fun String.storeState(): StoreState {
    return when(this) {
        StoreState.OPERATION.toString(), StoreState.OPERATION.state  -> StoreState.OPERATION
        else -> StoreState.CLOSED
    }
}

fun StoreState.isOperation(): Boolean {
    return when(this) {
        StoreState.OPERATION -> true
        else -> false
    }
}