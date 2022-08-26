package coroutine.shared_state

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * All access to shared data is confined to a single thread
 * Types of thread confinement:
 * - Fine-grained: each individual increment switches context (it is much slower)
 * - Coarse-grained: the whole function is run on a single thread, no context switching (faster)
 * */
fun main() {
    runBlocking {
        //fineGrained()

        coarseGrained()
    }
}

//much slower: atomic variable was 28ms this is 669ms
private suspend fun fineGrained() {
    val counterContext = newSingleThreadContext("CounterContext")
    var counter = 0
    withContext(Dispatchers.Default) {
        massiveRun {
            withContext(counterContext) {
                counter++
            }
        }
    }
    println("Counter = $counter")
}

//this finished in 33ms
private suspend fun coarseGrained() {
    val counterContext = newSingleThreadContext("CounterContext")
    var counter = 0
    withContext(counterContext) {
        massiveRun {
            counter++
        }
    }
    println("Counter = $counter")
}