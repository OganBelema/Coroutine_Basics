import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel

/**
 * Multiple coroutines can send value to the same channel
 * The order the value comes in is the order they are handled
 * */
fun main() {
    runBlocking {
        val channel = Channel<String>()
        launch { sendString(channel, "message1",200L) }
        launch { sendString(channel, "message2", 500L) }
        repeat(6) {
            println(channel.receive())
        }
        coroutineContext.cancelChildren()
    }
}

suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(s)
    }
}