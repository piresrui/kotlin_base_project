package com.rpires.projects.base.controllers

import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.web.client.RestTemplate

@ExtendWith(MockKExtension::class)
class HelloWorldControllerTest {

    @MockK
    private lateinit var restTemplate: RestTemplate

    private lateinit var helloWorldController: HelloWorldController

    @BeforeEach
    fun setup() {
        helloWorldController = HelloWorldController()
    }

    @Test
    fun `call to helloworld returns correctly`() {
        val got = helloWorldController.hello()

        assertNotNull(got)
        assertEquals(200, got.statusCodeValue)
        assertEquals("Hello!", got.body)
    }
}