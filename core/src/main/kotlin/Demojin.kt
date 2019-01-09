
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

/**
 * (Default template)
 * Created on 2019-01-08
 *
 * @author gyam
 */
fun main(args: Array<String>) {
    (0..Random().nextInt(30)).forEach {
        showln("${it}hello${Random().nextInt()}")
    }
    showln("test")
    showln { "provided" }
}

val isFirst = AtomicBoolean(true)
fun showln(contentLazy: () -> String) = showlnStack(contentLazy(), 1)
fun showln(content: String) = showlnStack(content, 1)
fun showlnStack(content: String, stackCount: Int) {
    val stack: StackTraceElement? = Thread.currentThread().stackTrace[2 + stackCount]
    val fileAndLine: String = stack?.let(::stackExpression) ?: "\$UnknownLine"
    val message = "\$show($fileAndLine)"

    val open = overrideStateOnFirstAccess()

    val newline = System.lineSeparator()
    doPrint(open, "\$show { $content }\tat ($fileAndLine)$newline")
}

private fun overrideStateOnFirstAccess(): Array<StandardOpenOption> {
    return if (isFirst.compareAndSet(true, false)) {
        arrayOf(StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE)

    } else {
        arrayOf(StandardOpenOption.APPEND, StandardOpenOption.CREATE)
    }
}

fun doPrint(open: Array<StandardOpenOption>, content: String) {
    Files.newBufferedWriter(Paths.get("show"), StandardCharsets.UTF_8, *open).use {
        it.write(content)
        print(content)
    }
}

fun stackExpression(stack: StackTraceElement): String {
    return stack.toString()
}
