import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = produce {
            for (x in 1..5)
                send(x * x)
        }

        //looping through channel to print elements
        loop(channel)

        //alternatively
        consumeEach(channel)
    }
}

private suspend fun consumeEach(channel: ReceiveChannel<Int>) {
    channel.consumeEach {
        println(it)
    }
}

private suspend fun loop(channel: ReceiveChannel<Int>) {
    for (y in channel)
        println(y)
}