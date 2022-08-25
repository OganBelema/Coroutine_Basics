import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

//refers to situations where you have more than one flow coming and you need to mix them together
fun main() {
    runBlocking {
        zipOperator()

        combineOperator()
    }
}

//zip: matches corresponding values of two flows
suspend fun zipOperator() {
    val english = flowOf("One", "Two", "Three")
    val french = flowOf("Un", "Deux", "Troix")
    english.zip(french) {a, b ->
        "$a in French is $b"
    }.collect {
        println(it)
    }
}

//combine: combine the latest value of one flow with the latest value of the other
suspend fun combineOperator() {
    val numbers = (1..5).asFlow()
        .onEach {
            delay(300L)
        }

    val values = flowOf("One", "Two", "Three", "Four", "Five")
        .onEach {
            delay(400L)
        }

    numbers.combine(values) {a, b ->
        "$a -> $b"
    }.collect {
        println(it)
    }
}