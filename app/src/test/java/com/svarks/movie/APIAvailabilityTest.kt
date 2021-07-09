package com.svarks.movie

import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset

class APIAvailabilityTest {
    @Test
    @Throws(Exception::class)
    fun testAvailability() {
        val connection = URL("https://api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=fnZBgyIOf8KKm6zVO8Lq91xnLLglOp7t").openConnection()
        val response = connection.getInputStream()
        val buffer = StringBuffer()
        BufferedReader(InputStreamReader(response, Charset.defaultCharset())).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line)
            }
        }
        assert(buffer.isNotEmpty())
    }
}
