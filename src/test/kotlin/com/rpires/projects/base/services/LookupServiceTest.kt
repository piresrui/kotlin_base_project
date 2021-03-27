package com.rpires.projects.base.services

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LookupServiceTest {

    private lateinit var lookupService: LookupService

    @BeforeEach
    fun setup() {
        lookupService = LookupService()
        lookupService.init()
    }

    @Test
    fun `test valid number`() {

        val (valid, prefix) = lookupService.isValid("44")
        assert(valid)
        assertEquals(prefix, "44")

        val (b, s) = lookupService.isValid("30000012")
        assert(b)
        assertEquals(s, "3000001")

    }

    @Test
    fun `test invalid number`() {
        val (valid, prefix) = lookupService.isValid("501")
        assertFalse(valid)
        assertNull(prefix)
    }

    @Test
    fun `test blank value`() {
        val (valid, prefix) = lookupService.isValid("")
        assertFalse(valid)
        assertNull(prefix)
    }

}