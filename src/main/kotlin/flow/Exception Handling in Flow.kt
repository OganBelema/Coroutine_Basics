import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import java.lang.Exception

fun main() {
    runBlocking {
        tryCatch()

        catch()

        onCompletion()
    }
}

suspend fun tryCatch() {
    try {
        (1..3).asFlow().onEach {
            check(it != 2)
        }.collect {
            println(it)
        }
    } catch (exception: Exception) {
        println("Caught exception $exception")
    }
}

//catches any error that happens above the catch operator not below
suspend fun catch() {
    (1..3).asFlow().onEach {
        check(it != 2)
    }.catch { exception ->
        println("Caught exception $exception")
    }.collect {
        println(it)
    }
}

//onCompletion is the equivalent of a finally block
//it does not catch exception
//it runs whether or not there is exception in code
suspend fun onCompletion() {
    (1..3).asFlow().onEach {
        check(it != 2)
    }.onCompletion { cause ->
        if (cause != null)
            println("Flow completed with $cause")
        else
            println("Flow completed successfully")
    }.catch { exception ->
        println("Caught exception $exception")
    }.collect {
        println(it)
    }
}