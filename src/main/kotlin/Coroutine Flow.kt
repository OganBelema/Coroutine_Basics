import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Receiving prime numbers")
        sendPrimes().collect {
            println("Received prime number $it")
        }

        println("Finished receiving the prime numbers")

        println("Receiving numbers")

        sendNumbers().collect {
            println("Received $it")
        }

        println("Finished receiving numbers")

        println("Receiving greetings...")

        sendGreetings().collect {
            println(it)
        }

        println("Done receiving greetings")
    }
}

//Creating flows

fun sendPrimes(): Flow<Int> = flow {
    val primesList = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
    primesList.forEach {
        delay(it*100L)
        emit(it)
    }
}

fun sendNumbers() = listOf(1, 2, 3).asFlow()

fun sendGreetings() = flowOf("Hello Sam", "Hello Tom", "Hello Sandra")
