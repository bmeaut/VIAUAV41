import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

val flow = flow {
    for (i in 1..10) {
        delay(1000)
        emit(i)
    }
}

inline fun <T> Flow<T>.filterValues(crossinline predicate: (T) -> Boolean): Flow<T> {
    return flow {
        this@filterValues.collect {
            if (predicate(it)) emit(it)
        }
    }
}

inline fun <T> Flow<T>.filterTransform(crossinline predicate: (T) -> Boolean): Flow<T> = transform {
    if (predicate(it)) emit(it)
}

fun main() {
    runBlocking {
        flow.filterValues { it % 2 == 0 }
            .collect { println(it) }
    }

    runBlocking {
        flowOf("flows", "are", "cool")
            .transform { word ->
                for (char in word) {
                    emit(char)
                }
            }
            .collect(::println)
    }
}
