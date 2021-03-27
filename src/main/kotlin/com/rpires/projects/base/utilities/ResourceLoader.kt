package com.rpires.projects.base.utilities

import java.io.File
import java.net.URI

internal object ResourceLoader {

    fun getFileResources(fileName: String): List<String> =
        File(getResource(fileName)).readLines()

    private fun getResource(file: String): URI =
        ResourceLoader.javaClass.classLoader.getResource(file)?.toURI() ?: throw RuntimeException("Error fetching resource")
}