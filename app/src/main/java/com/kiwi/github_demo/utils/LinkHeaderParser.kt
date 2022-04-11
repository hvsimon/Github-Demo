package com.kiwi.github_demo.utils

import java.util.regex.Pattern

object LinkHeaderParser {

    private val LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
    private val SINCE_PATTERN = Pattern.compile("\\bsince=(\\d+)")
    private const val NEXT_LINK = "next"

    fun String.extractLinks(): Map<String, String> {
        val links = mutableMapOf<String, String>()
        val matcher = LINK_PATTERN.matcher(this)

        while (matcher.find()) {
            val count = matcher.groupCount()
            if (count == 2) {
                links[matcher.group(2)!!] = matcher.group(1)!!
            }
        }
        return links
    }

    fun Map<String, String>.nextSinceValue(): Int? {
        return this[NEXT_LINK]?.let { next ->
            val matcher = SINCE_PATTERN.matcher(next)
            if (!matcher.find() || matcher.groupCount() != 1) {
                null
            } else {
                try {
                    Integer.parseInt(matcher.group(1)!!)
                } catch (ex: NumberFormatException) {
                    null
                }
            }
        }
    }
}
