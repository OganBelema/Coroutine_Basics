import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job1 = launch {
            delay(3000L)
            println("Job1 launched")
        }

        job1.invokeOnCompletion {
            println("Job1 is completed")
        }

        delay(500L)

        println("Job1 will be canceled")

        job1.cancel()
    }
}