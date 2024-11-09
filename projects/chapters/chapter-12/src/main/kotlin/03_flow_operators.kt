package operators

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val flow = flow {
    for (i in 1..10) {
        delay(100) // TODO set to 1000
        emit(i)
    }
}

fun basicOperators() {
    runBlocking {
//        flow.filter { it % 2 == 0 }
//            .collect { println(it) }

        flow.filter { it % 2 == 0 }
            .map { it * 10 }
            .collect { println(it) }
    }
}

fun mapExample() {
//    runBlocking {
//        flow.filter { it % 2 == 0 }
//            .collect { println(it) }
//    }
}

fun main() {
    basicOperators()
    mapExample()
}
