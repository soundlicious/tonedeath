package dev.expositopablo.tonedeath.data.db

import androidx.lifecycle.LiveData

import dev.expositopablo.tonedeath.data.commons.Pinyin

interface DataManager {
    val score: Int

    val allPinyin: LiveData<List<Pinyin>>
    val allDistinctInitial: LiveData<List<String>>
    val allDistinctFinal: LiveData<List<String>>
    val rowCount: LiveData<Int>
    val randomPinyin: Pinyin


    fun initDB()

    fun saveScore(score: Int)

    fun getMainList(isInitialFirst: Boolean): LiveData<List<String>>
    fun getDetailList(item: String, isInitialFirst: Boolean): LiveData<List<Pinyin>>

    fun getAllPinyinByInitial(pInitial: String): LiveData<List<Pinyin>>
    fun getAllPinyinByFinal(pFinal: String): LiveData<List<Pinyin>>
}
