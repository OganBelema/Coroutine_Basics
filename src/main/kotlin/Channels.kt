import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = Channel<Int>()

        launch {
            for (x in 1..5)
                channel.send(x * x)
        }

        for (i in 1..5)
            println(channel.receive())
    }
}