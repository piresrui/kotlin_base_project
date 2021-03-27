package com.rpires.projects.base.services

import com.rpires.projects.base.data.NumberModel
import org.springframework.stereotype.Service

@Service
class ValidatorService(
    private val lookupService: LookupService
) {

    /**
     * Returns pair of bool to NumberModel
     *
     * The boolean is true if the number is valid and then the NumberModel will contain
     * the relevant data.
     * If the boolean is false then the data in NumberModel is not relevant.
     */
    fun validateString(number: String): Pair<Boolean, NumberModel?> {

        var clearString =  number.replace("\\s".toRegex(), "")

        if (clearString.isEmpty() || clearString.isBlank() || !clearString.matches("[+0-9]+".toRegex())) {
            return false to null
        }

        clearString = when {
            clearString.startsWith("+") -> {
                clearString.removeRange(0, 1)
            }
            clearString.startsWith("00") -> {
                clearString.removeRange(0, 2)
            }
            else -> {
                return false to null
            }
        }

        return isValid(clearString).let {
            it.first to NumberModel(
                number,
                clearString,
                it.second
            )
        }

    }

    private fun isValid(number: String): Pair<Boolean, String?> {
        return lookupService.isValid(number)
    }

}