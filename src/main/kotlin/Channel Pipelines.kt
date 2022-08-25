import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val numbers = produceNumbers()

        val squares = square(numbers)

        for (i in 1..5)
            println(squares.receive())

        println("Done!")
        coroutineContext.cancel()
    }
}

fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true)
        send(x++)
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
    for (x in numbers)
        send(x * x)
}