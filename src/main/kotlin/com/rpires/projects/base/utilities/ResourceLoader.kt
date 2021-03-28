package com.rpires.projects.base.utilities

import java.io.InputStream

internal object ResourceLoader {

    fun getFileResources(fileName: String): List<String> {
        val lineList: MutableList<String> = mutableListOf()
        getResource(fileName).bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

        return lineList
    }

    private fun getResource(file: String): InputStream =
        ResourceLoader.javaClass.classLoader.getResourceAsStream(file) ?: throw RuntimeException("Error fetching resource")
}