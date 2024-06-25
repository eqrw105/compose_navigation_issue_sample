package com.example.sample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Test

abstract class Base(val error: String? = null)

data class Sample(val id : String): Base(null)

fun Sample.toId(): String {
    return id
}

public inline fun <T, R> Flow<T>.sampleMap(crossinline transform: suspend (value: T) -> R): Flow<R> = map { value ->
    if (value is Base && value.error != null) {
        throw Exception("error")
    } else {
        transform(value)
    }
}
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        runBlocking {
            flowOf(Sample("abc"))
                .sampleMap {
                    it.toId()
                }
                .catch {
                    println("TEST -> catch ${it.message}")
                }
                .collect {
                    println("TEST -> collect $it")
                }
        }
    }
}

