package dev.expositopablo.tonedeath.views.practice

import dev.expositopablo.tonedeath.data.commons.PinyinDTO

interface PracticeCallback {
    fun gameOver(currentPiyin: PinyinDTO, answer: PinyinDTO, score: Int)
    fun backToGame()
}
