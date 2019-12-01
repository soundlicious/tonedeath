package dev.expositopablo.tonedeath.data.db

import android.content.SharedPreferences
import dev.expositopablo.tonedeath.androidutils.ext.SchedulerProvider
import dev.expositopablo.tonedeath.androidutils.ext.with

import dev.expositopablo.tonedeath.data.commons.Constants
import dev.expositopablo.tonedeath.data.commons.Pinyin
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao
import io.reactivex.Single

class AppDataManager constructor(
        db: PinyinDatabase,
        private val pref: SharedPreferences,
        private val schedulerProvider: SchedulerProvider
) : DataManager {
    private val pinyinDao: PinyinDao = db.pinyinDao()

    override val score: Single<Int>
        get() = Single.just(pref.getInt(Constants.SCORE, 0)).with(schedulerProvider)

    override val allPinyin: Single<List<Pinyin>>
        get() = pinyinDao.allPinyin.with(schedulerProvider)

    override val allDistinctInitial: Single<List<String>>
        get() = pinyinDao.allDistinctInitial.with(schedulerProvider)

    override val allDistinctFinal: Single<List<String>>
        get() = pinyinDao.allDistinctFinal.with(schedulerProvider)

    override val rowCount: Single<Int>
        get() = pinyinDao.rowCount.with(schedulerProvider)

    override val randomPinyin: Single<Pinyin>
        get() = pinyinDao.randomPinyin.with(schedulerProvider)

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
            pinyinDao.getAllPinyinByInitial(item).with(schedulerProvider)
        else
            pinyinDao.getAllPinyinByFinal(item).with(schedulerProvider)
    }

    override fun getAllPinyinByInitial(pInitial: String): Single<List<Pinyin>> {
        return pinyinDao.getAllPinyinByInitial(pInitial).with(schedulerProvider)
    }

    override fun getAllPinyinByFinal(pFinal: String): Single<List<Pinyin>> {
        return pinyinDao.getAllPinyinByFinal(pFinal).with(schedulerProvider)
    }
}
