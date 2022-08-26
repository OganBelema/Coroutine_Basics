import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

//in case processing a flow takes a long time
//a buffer is useful to accumulate flow values
//that can be processed later
fun main() {
    runBlocking {
        //simulating a scenario where processing takes longer than emission
        val time = measureTimeMillis {
            generate()
                .buffer()
                .collect {
                delay(300L)
                println(it)
            }
        }

        println("Collected in $time")
    }
}

private fun generate() = flow {
    for (i in 1..3) {
        delay(100L)
        emit(i)
    }
}