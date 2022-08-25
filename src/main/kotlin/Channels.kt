import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        //sendAndReceive()

        sendAndClose()
    }
}

private suspend fun CoroutineScope.sendAndReceive() {
    val channel = Channel<Int>()

    launch {
        for (x in 1..5)
            channel.send(x * x)
    }

    for (i in 1..5)
        println(channel.receive())
}

private suspend fun CoroutineScope.sendAndClose() {
    val channel = Channel<Int>()

    launch {
        for (x in 1..5)
            channel.send(x * x)
        channel.close()
    }

    for (i in channel)
        println(i)
}