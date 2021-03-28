package com.rpires.projects.base.services

import com.rpires.projects.base.utilities.ResourceLoader
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class LookupService {

    private var fastLookup: MutableList<String> = mutableListOf()
    private var lLookup: MutableSet<String> = mutableSetOf()

    @PostConstruct
    fun init() {

        ResourceLoader.getFileResources("prefixes.txt").forEach {
            if (it.length < 7) {
                fastLookup.add(it)
            } else {
                lLookup.add(it)
            }
        }
    }

    /**
     * Returns true and prefix if number has a valid prefix
     *
     * We do a fast lookup on a list of strings, which despite being O(n^2) complexity, is fast since we have only 4 strings of 2 digits.
     * If this fails, we do the lookup on the set.
     */
    fun isValid(number: String): Pair<Boolean, String?> {
        var (valid, prefix) = fastLookup.find { number.startsWith(it) }.let {
                !it.isNullOrEmpty() to it
        }

        return if (!valid && number.length >= 7) {
            prefix = number.subSequence(0, 7).toString()
            lLookup.contains(prefix) to prefix
        } else {
            valid to prefix
        }

    }


}