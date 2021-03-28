package com.rpires.projects.base

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient


class IntegrationTest {

    private lateinit var webClient: WebClient

    @BeforeEach
    fun setup() {
        webClient = WebClient.builder().baseUrl("http://localhost:8080").build()
    }

    @Test
    fun `test empty call`() {
        val response = fetch(emptyList())

        assertEquals(mapOf<String, Map<String, Int>>(), response)
    }


    @Test
    fun `test invalid strings`() {
        val response = fetch(listOf("lol", "not ok", "bananas!"))

        assertEquals(mapOf<String, Map<String, Int>>(), response)
    }

    @Test
    fun `test real call example 1`() {
        val expected: Map<String, Map<String, Int>> = mapOf(
            "1" to mapOf("Technology" to 1, "Banking" to 1),
            "3519173" to mapOf("Clothing" to 2)
        )

        val response = fetch(listOf("+1983236248", "+1 7490276403", "001382355A", "+351917382672", "+35191734022"))


        assertEquals(expected.keys, response!!.keys)

        response.keys.forEach {
            val exp = expected[it]
            val act = response[it]

            // safe because we know the keys exist
            assertEquals(HashSet(exp!!.keys), HashSet(act!!.keys))
            assertEquals(HashSet(exp.values), HashSet(act.values))
        }

    }

    @Test
    fun `test real call example 2`() {
        val expected: Map<String, Map<String, Int>> = mapOf(
            "1" to mapOf("Technology" to 2, "Clothing" to 1),
            "44" to mapOf("Banking" to 1)
        )

        val response = fetch(listOf("+1983248", "001382355", "+147 8192", "+4439877"))

        assertEquals(expected.keys, response!!.keys)

        response.keys.forEach {
            val exp = expected[it]
            val act = response[it]

            // safe because we know the keys exist
            assertEquals(HashSet(exp!!.keys), HashSet(act!!.keys))
            assertEquals(HashSet(exp.values), HashSet(act.values))
        }

    }

    private inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}

    private fun fetch(numbers: List<String>): Map<String, Map<String, Int>>? {
        return webClient.post()
            .uri("/aggregate")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(numbers))
            .retrieve()
            .bodyToMono(typeReference<Map<String, Map<String, Int>>>())
            .block()

    }
}