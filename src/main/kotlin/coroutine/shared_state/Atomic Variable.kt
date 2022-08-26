package coroutine.shared_state

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

/**
 * The easiest solution is to use an atomic variable for the counter
 * An atomic variable is a variable that can only be updated from one thread at a time
 * This means if there are multiple coroutines trying to update the atomic variable at a time,
 * they will have to wait each other's turn
 * Works with primitive data types and collections
 * */
fun main() {
    runBlocking {
        val counter = AtomicInteger(0)
        withContext(Dispatchers.Default) {
            massiveRun {
                counter.incrementAndGet()
            }
        }
        println("Counter = $counter")
    }
}

