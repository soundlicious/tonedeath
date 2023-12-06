package dev.pabloexposito.model.data

data class PinYinSoundInfo(
    val pinyin: Pinyin,
    val tone: Tone,
    val voiceGender: dev.pabloexposito.core.data.VoiceGender,
    val voiceNumber: VoiceNumber,
) {
    val audioResName =
        "${pinyin}_${tone.name.lowercase()}_voice_${voiceGender.ordinal}_${voiceNumber.ordinal + 1}"

    fun withNewTone(tone: Tone) = copy(tone = tone)

    fun withNewGender(voiceGender: dev.pabloexposito.core.data.VoiceGender) = copy(voiceGender = voiceGender)

    fun withNewVoiceNumber(voiceNumber: VoiceNumber) = copy(voiceNumber = voiceNumber)
}
