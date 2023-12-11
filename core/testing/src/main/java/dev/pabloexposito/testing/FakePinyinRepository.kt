package dev.pabloexposito.testing

import dev.pabloexposito.core.data.VoiceGender
import dev.pabloexposito.data.repository.PinyinRepository
import dev.pabloexposito.model.data.PinYinSoundInfo
import dev.pabloexposito.model.data.Pinyin
import dev.pabloexposito.model.data.Tone
import dev.pabloexposito.model.data.VoiceNumber

class FakePinyinRepository : PinyinRepository {
    private val pinyinList = listOf(
        Pinyin("a", "b"),
        Pinyin("c", "d"),
        Pinyin("e", "f"),
        Pinyin("g", "h"),
        Pinyin("i", "j"),
        Pinyin("k", "l"),
        Pinyin("m", "n"),
        Pinyin("o", "p"),
        Pinyin("q", "r"),
        Pinyin("s", "t"),
        Pinyin("u", "v"),
        Pinyin("w", "x"),
        Pinyin("y", "z"),
    )
    private var index = 0
    val currentPinyin: Pinyin
        get() = pinyinList[index]
    val currentSoundInfo: PinYinSoundInfo
        get() = PinYinSoundInfo(
            currentPinyin,
            tone = Tone.TONE2,
            voiceGender = VoiceGender.FEMININ,
            voiceNumber = VoiceNumber.TWO,
        )

    override fun getRandomPinyin(): Pinyin {
        val index = if (index == pinyinList.size - 1) {
            0
        } else {
            index++
        }
        return pinyinList[index]
    }

    override fun getRandomPinYinSoundInfo(): PinYinSoundInfo {
        getRandomPinyin()
        return currentSoundInfo
    }

    override fun getInitials(): List<String> {
        return pinyinList.map { it.initial }
    }

    override fun getFinalsByInitials(initial: String): List<String> {
        return pinyinList.filter { it.initial == initial }.map { it.final }
    }
}