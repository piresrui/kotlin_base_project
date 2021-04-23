package com.rpires.projects.base.controllers

import com.rpires.projects.base.data.HelperModel
import com.rpires.projects.base.services.SectorService
import com.rpires.projects.base.services.ValidatorService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class AggregateController(
    private val validatorService: ValidatorService,
    private val sectorService: SectorService
) {

    private val logger = LoggerFactory.getLogger(AggregateController::class.java)


    @PostMapping(path=["/aggregate"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun aggregate(@RequestBody numbers: List<String>): ResponseEntity<MutableMap<String, MutableMap<String, Int>>> {

        logger.info("Request for /aggregate with $numbers")

        val response: MutableMap<String, MutableMap<String, Int>> = mutableMapOf()

        numbers.stream()
            .map { validatorService.validateString(it) }
            .filter { it.first && it.second != null }
            // This is sketchy but safe since we know the second will never reach here as null
            .map { HelperModel(it.second!!.prefix!!, sectorService.getSector(it.second!!.number)!!) }
            .forEach {
                response.putIfAbsent(it.prefix, mutableMapOf())
                response[it.prefix]!!.inc(it.model.sector)
            }

        return ResponseEntity.ok(response)
    }


    fun <T> MutableMap<T, Int>.inc(key: T, more: Int = 1) = merge(key, more, Int::plus)
}