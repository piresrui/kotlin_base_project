package com.rpires.projects.base.controllers

import com.rpires.projects.base.api.BusinessSectorModel
import com.rpires.projects.base.data.NumberModel
import com.rpires.projects.base.services.LookupService
import com.rpires.projects.base.services.SectorService
import com.rpires.projects.base.services.ValidatorService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate

@ExtendWith(MockKExtension::class)
class AggregateControllerTest {

    @MockK
    private lateinit var lookupService: LookupService
    @MockK
    private lateinit var validatorService: ValidatorService
    @MockK
    private lateinit var sectorService: SectorService

    @InjectMockKs
    private lateinit var aggregateController: AggregateController

    @BeforeEach
    fun setup() {
        aggregateController = AggregateController(validatorService, sectorService)
    }


    @Test
    fun `test correct call to aggregate`() {

        val expected: MutableMap<String, MutableMap<String, Int>> = mutableMapOf()
        expected["44"] = mutableMapOf("Business" to 1)

        every { sectorService.getSector("+44 00 00 00") } returns BusinessSectorModel("+44 00 00 00","Business")
        every { lookupService.isValid("44000000") } returns (true to "44")
        every { validatorService.validateString("+44 00 00 00") } returns (true to NumberModel("+44 00 00 00", "44000000", "44"))

        val got = aggregateController.aggregate(listOf("+44 00 00 00"))

        assertEquals(HttpStatus.OK, got.statusCode)
        assertEquals(expected, got.body)

    }

    @Test
    fun `test call to aggregate with invalid number`() {

        val expected: MutableMap<String, MutableMap<String, Int>> = mutableMapOf()

        every { sectorService.getSector("+44 AA 00 00") } returns BusinessSectorModel("+44 AA 00 00","Business")
        every { validatorService.validateString("+44 AA 00 00") } returns (false to null)

        val got = aggregateController.aggregate(listOf("+44 AA 00 00"))

        assertEquals(HttpStatus.OK, got.statusCode)
        assertEquals(expected, got.body)

    }
}