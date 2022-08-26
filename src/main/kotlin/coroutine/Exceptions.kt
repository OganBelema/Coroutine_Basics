import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException

fun main() {
    runBlocking {
        val myHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception handled: ${throwable.localizedMessage}")
        }

        val job = GlobalScope.launch(myHandler) {
            println("Throwing exception from job")
            throw IndexOutOfBoundsException("Exception from executing job")
        }

        job.join()

        val deferred = GlobalScope.async {
            println("Throwing exception from async")
            throw IllegalArgumentException("Exception from executing async")
        }

        try {
            deferred.await()
        } catch (exception: Exception) {
            println("Deferred Exception caught: ${exception.localizedMessage}")
        }
    }
}