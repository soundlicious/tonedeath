package dev.expositopablo.tonedeath.data.db

import android.content.SharedPreferences

import dev.expositopablo.tonedeath.data.commons.Constants
import dev.expositopablo.tonedeath.data.commons.Pinyin
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao
import io.reactivex.Single

class AppDataManager constructor(private val db: PinyinDatabase, private val pref: SharedPreferences) : DataManager {
    private val pinyinDao: PinyinDao = db.pinyinDao()

    override val score: Int
        get() = pref.getInt(Constants.SCORE, 0)

    override val allPinyin: Single<List<Pinyin>>
        get() = pinyinDao.allPinyin

    override val allDistinctInitial: Single<List<String>>
        get() = pinyinDao.allDistinctInitial

    override val allDistinctFinal: Single<List<String>>
        get() = pinyinDao.allDistinctFinal

    override val rowCount: Single<Int>
        get() = pinyinDao.rowCount

    override val randomPinyin: Single<Pinyin>
        get() = pinyinDao.randomPinyin

    override fun saveScore(score: Int) {
        val best = pref.getInt(Constants.SCORE, 0)
        if (score > best)
            pref.edit().putInt(Constants.SCORE, score).apply()
    }

    override fun getMainList(isInitialFirst: Boolean): Single<List<String>> {
        return if (isInitialFirst)
            pinyinDao.allDistinctInitial
        else
            pinyinDao.allDistinctFinal
    }

    override fun getDetailList(item: String, isInitialFirst: Boolean): Single<List<Pinyin>> {
        return if (isInitialFirst)
            pinyinDao.getAllPinyinByInitial(item)
        else
            pinyinDao.getAllPinyinByFinal(item)
    }

    override fun getAllPinyinByInitial(pInitial: String): Single<List<Pinyin>> {
        return pinyinDao.getAllPinyinByInitial(pInitial)
    }

    override fun getAllPinyinByFinal(pFinal: String): Single<List<Pinyin>> {
        return pinyinDao.getAllPinyinByFinal(pFinal)
    }
}
