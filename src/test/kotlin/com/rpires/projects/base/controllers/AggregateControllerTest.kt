package com.rpires.projects.base.controllers

import com.rpires.projects.base.services.LookupService
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.web.client.RestTemplate

@ExtendWith(MockKExtension::class)
class AggregateControllerTest {

    @MockK
    private lateinit var restTemplate: RestTemplate

    @InjectMockKs
    private lateinit var lookupService: LookupService

    private lateinit var aggregateController: AggregateController

    @BeforeEach
    fun setup() {
        //aggregateController = AggregateController(lookupService)
    }

}