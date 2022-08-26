package coroutine.shared_state

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * A mutual exclusion lock allows access to a sensitive part of the code through a lock and unlock system
 * A coroutine locks the variable to update it, then unlocks it after updating
 * it can only be unlocked by the coroutine that locked it
 * */
fun main() {
    runBlocking {
        val mutex = Mutex()
        var counter = 0
        withContext(Dispatchers.Default) {
            massiveRun {
                mutex.withLock {
                    counter++
                }
            }
        }
        println("Counter = $counter")
    }
}