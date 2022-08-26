package coroutine.shared_state

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * Multiple coroutines can update a shared state variable concurrently
 * However, some updates may be lost
 * */
fun main() {
    //This shows the shared state problem
    //expected counter result is 100,000
    //actual result was 59100 in my case

    runBlocking {
        var counter = 0
        withContext(Dispatchers.Default) {
            massiveRun {
                counter++
            }
        }
        println("Counter = $counter")
    }
}


suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }

    println("Completed ${n * k} actions in $time ms")
}