package com.rpires.projects.base.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    @Value("\${application.external-systems.business-sector.host}")
    private val host: String
) {

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient = builder
            .baseUrl(host)
            .build()

}