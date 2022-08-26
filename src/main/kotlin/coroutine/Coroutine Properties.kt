import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() {
    runBlocking {
        //Flow properties: Do not start until collect is called i.e they are cold
        val numbersFlow = sendNewNumbers()
        println("Flow hasn't started yet")

        println("Starting flow now...")
        numbersFlow.collect{ println(it) }

        //Flow cannot be canceled by themselves. You have to cancel the coroutine that contains them
        withTimeoutOrNull(1000L) {
            sendNewGreetings().collect {
                println(it)
            }
        }
    }
}

fun sendNewNumbers() = flow {
    listOf(1, 3, 5, 7).forEach {
        emit(it)
    }
}

fun sendNewGreetings() = flow {
    listOf("Hello Musa", "Hello Ahmed", "Hello Frank").forEach {
        delay(400L)
        emit(it)
    }
}