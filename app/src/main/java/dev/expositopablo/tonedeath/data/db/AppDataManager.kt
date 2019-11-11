package dev.expositopablo.tonedeath.data.db

import androidx.lifecycle.LiveData
import android.content.SharedPreferences

import dev.expositopablo.tonedeath.data.commons.Constants
import dev.expositopablo.tonedeath.data.commons.Pinyin
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao

class AppDataManager constructor(private val db: PinyinDatabase, private val pref: SharedPreferences) : DataManager {
    private val pinyinDao: PinyinDao = db.pinyinDao()

    override val score: Int
        get() = pref.getInt(Constants.SCORE, 0)

    override val allPinyin: LiveData<List<Pinyin>>
        get() = pinyinDao.allPinyin

    override val allDistinctInitial: LiveData<List<String>>
        get() = pinyinDao.allDistinctInitial

    override val allDistinctFinal: LiveData<List<String>>
        get() = pinyinDao.allDistinctFinal

    override val rowCount: LiveData<Int>
        get() = pinyinDao.rowCount

    override val randomPinyin: Pinyin
        get() = pinyinDao.randomPinyin

    override fun initDB() {
        db.populateDb()
    }

    override fun saveScore(score: Int) {
        val best = pref.getInt(Constants.SCORE, 0)
        if (score > best)
            pref.edit().putInt(Constants.SCORE, score).apply()
    }

    override fun getMainList(isInitialFirst: Boolean): LiveData<List<String>> {
        return if (isInitialFirst)
            pinyinDao.allDistinctInitial
        else
            pinyinDao.allDistinctFinal
    }

    override fun getDetailList(item: String, isInitialFirst: Boolean): LiveData<List<Pinyin>> {
        return if (isInitialFirst)
            pinyinDao.getAllPinyinByInitial(item)
        else
            pinyinDao.getAllPinyinByFinal(item)
    }

    override fun getAllPinyinByInitial(pInitial: String): LiveData<List<Pinyin>> {
        return pinyinDao.getAllPinyinByInitial(pInitial)
    }

    override fun getAllPinyinByFinal(pFinal: String): LiveData<List<Pinyin>> {
        return pinyinDao.getAllPinyinByFinal(pFinal)
    }
}
