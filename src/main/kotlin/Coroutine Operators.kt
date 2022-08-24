import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() {
    //operators take an input flow, transform it and return an output flow
    //operators are cold and do not execute until flow is collected
    runBlocking {

        mapOperator()

        filterOperator()

        transformOperator()

    }
}

private suspend fun transformOperator() {
    //transform
    //can emit any value at any point

    (1..10).asFlow().transform {
        emit("Emitting string value $it")
        emit(it)
    }.collect {
        println(it)
    }
}

private suspend fun filterOperator() {
    //filter
    (1..10).asFlow().filter {
        it % 2 == 0
    }.collect {
        println(it)
    }
}

private suspend fun mapOperator() {
    //map
    //takes an input data and maps it into another output
    (1..10).asFlow().map {
        delay(500L)
        "napping $it"
    }.collect {
        println(it)
    }
}