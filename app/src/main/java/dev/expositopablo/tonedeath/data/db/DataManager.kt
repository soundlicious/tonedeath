package dev.expositopablo.tonedeath.data.db

import dev.expositopablo.tonedeath.data.commons.Pinyin
import io.reactivex.Single

interface DataManager {
    val score: Int

    val allPinyin: Single<List<Pinyin>>
    val allDistinctInitial: Single<List<String>>
    val allDistinctFinal: Single<List<String>>
    val rowCount: Single<Int>
    val randomPinyin: Single<Pinyin>

    fun saveScore(score: Int)

    fun getMainList(isInitialFirst: Boolean): Single<List<String>>
    fun getDetailList(item: String, isInitialFirst: Boolean): Single<List<Pinyin>>

    fun getAllPinyinByInitial(pInitial: String): Single<List<Pinyin>>
    fun getAllPinyinByFinal(pFinal: String): Single<List<Pinyin>>
}
