package com.rpires.projects.base.api

import com.fasterxml.jackson.annotation.JsonProperty

data class BusinessSectorModel(
    @JsonProperty("number")
    var number: String,
    @JsonProperty("sector")
    var sector: String
)
