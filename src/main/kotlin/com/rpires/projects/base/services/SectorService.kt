package com.rpires.projects.base.services

import com.rpires.projects.base.api.BusinessSectorModel
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class SectorService(
    private val webClient: WebClient
) {

    fun getSector(number: String): BusinessSectorModel? {
        return webClient.get()
            .uri("/sector/$number")
            .retrieve()
            .bodyToMono(BusinessSectorModel::class.java)
            .block()
    }

}