package com.rpires.projects.base.services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ValidatorServiceTest {

    @MockK
    private lateinit var lookupService: LookupService

    @InjectMockKs
    private lateinit var validatorService: ValidatorService

    @BeforeEach
    fun setup() {
        validatorService = ValidatorService(lookupService)
    }


    @Test
    fun `test valid string is cleared and valid`() {
        val number = "+44 00 00 00"

        every { lookupService.isValid("44000000") } returns (true to "44")

        val response = validatorService.validateString(number)

        assert(response.first)
        assertEquals(number, response.second!!.number)
        assertEquals("44", response.second!!.prefix)
        assertEquals("44000000", response.second!!.cleared )

    }

    @Test
    fun `test valid string is cleared and valid with prefix 00`() {
        val number = "0044 00 00 00"

        every { lookupService.isValid("44000000") } returns (true to "44")

        val response = validatorService.validateString(number)

        assert(response.first)
        assertEquals(number, response.second!!.number)
        assertEquals("44", response.second!!.prefix)
        assertEquals("44000000", response.second!!.cleared )

    }

    @Test
    fun `test invalid string is cleared and invalid`() {
        val number = "+42 00 00 00"

        every { lookupService.isValid("42000000") } returns (false to "42")

        val response = validatorService.validateString(number)

        assertFalse(response.first)
        assertEquals(number, response.second!!.number)
        assertEquals("42", response.second!!.prefix)
        assertEquals("42000000", response.second!!.cleared )
    }
}