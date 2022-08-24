import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    //operators take an input flow, transform it and return an output flow
    //operators are cold and do not execute until flow is collected
    runBlocking {

        mapOperator()

        filterOperator()

        transformOperator()

        takeOperator()

        reduceOperator()

        flowOnOperator()

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

private suspend fun takeOperator() {
    //takes only the specified number and disregards the rest
    (1..10).asFlow()
        .take(3)
        .collect {
            println(it)
        }
}

private suspend fun reduceOperator() {
    val size = 10
    val factorial = (1..10).asFlow()
        .reduce { accumulator, value ->
            accumulator * value
        }

    println("Factorial of $size is $factorial")
}

private suspend fun flowOnOperator() {
    //specifies the thread to execute the flow on
    (1..10).asFlow()
        .flowOn(Dispatchers.IO)
        .collect {
            println(it)
        }
}