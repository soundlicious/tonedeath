package dev.pabloexposito.model.data

data class Pinyin(
    val initial: String,
    val final: String
){
    override fun toString(): String {
        return "$initial$final"
            .replace("∅", "", true)
            .replace("ü", "v", true)
    }
}