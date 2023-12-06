package dev.pabloexposito.model.data

import dev.pabloexposito.core.data.VoiceGender


fun Pinyin.getRandomSoundInfo() = PinYinSoundInfo(
    pinyin = this,
    tone = Tone.values().random(),
    voiceGender = VoiceGender.values().random(),
    voiceNumber = VoiceNumber.values().random(),
)

fun Pinyin.getRandomSoundInfoWithSpecificTone(tone: Tone) = PinYinSoundInfo(
    pinyin = this,
    tone = tone,
    voiceGender = VoiceGender.values().random(),
    voiceNumber = VoiceNumber.values().random(),
)