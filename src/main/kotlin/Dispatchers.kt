import kotlinx.coroutines.*

fun main() {
    runBlocking {
        //need to configure in gradle for intellij IDEA when not android project
        /*launch(Dispatchers.Main) {
            println("Main dispatcher, Thread: ${Thread.currentThread().name}")
        }*/

        launch(Dispatchers.Unconfined) {
            println("Unconfined dispatcher, Initial thread: ${Thread.currentThread().name}")
            delay(100L)
            println("Unconfined dispatcher, New thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default){
            println("Default dispatcher, Thread: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO){
            println("IO dispatcher, Thread: ${Thread.currentThread().name}")
        }

        //expensive call
        launch(newSingleThreadContext("myThread")){
            println("NewSingleThreadContext dispatcher, Thread: ${Thread.currentThread().name}")
        }
    }
}