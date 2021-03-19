package com.rpires.projects.base.controllers

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloWorldController {

    private val logger = LoggerFactory.getLogger(HelloWorldController::class.java)


    @GetMapping(path=["/helloworld"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello!")
    }

}