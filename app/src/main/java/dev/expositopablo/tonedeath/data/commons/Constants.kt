package dev.expositopablo.tonedeath.data.commons

import java.util.ArrayList

object Constants {
    private val PACKAGE = "dev.expositopablo.tonedeath"
    val TONEDEATH_PREF = "$PACKAGE.TONEDEATH_PREF"
    val SCORE = "$PACKAGE.TONEDEATH_SCORE"
    val TONES: ArrayList<String> = object : ArrayList<String>() {
        init {
            add("tone0")
            add("tone1")
            add("tone2")
            add("tone3")
        }
    }
}
