package dev.expositopablo.tonedeath.data.db;

import android.arch.lifecycle.LiveData;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dev.expositopablo.tonedeath.data.commons.Constants;
import dev.expositopablo.tonedeath.data.commons.Pinyin;
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao;

@Singleton
public class AppDataManager implements DataManager {
    private final SharedPreferences pref;
    private final PinyinDao pinyinDao;

    @Inject
    public AppDataManager(PinyinDatabase db, SharedPreferences pref) {
        pinyinDao = db.pinyinDao();
        this.pref = pref;
    }

    @Override
    public void saveScore(int score) {
        int best = pref.getInt(Constants.SCORE, 0);
        if (score > best)
            pref.edit().putInt(Constants.SCORE, score).apply();
    }

    @Override
    public int getScore() {
        return pref.getInt(Constants.SCORE, 0);
    }

    @Override
    public LiveData<List<Pinyin>> getAllPinyin() {
        return pinyinDao.getAllPinyin();
    }

    public LiveData<List<String>> getAllDistinctInitial() {
        return pinyinDao.getAllDistinctInitial();
    }

    @Override
    public LiveData<List<String>> getAllDistinctFinal() {
        return pinyinDao.getAllDistinctFinal();
    }

    @Override
    public LiveData<List<String>> getMainList(Boolean isInitialFirst) {
        LiveData<List<String>> mainList;
        if (isInitialFirst)
            mainList = pinyinDao.getAllDistinctInitial();
        else
            mainList = pinyinDao.getAllDistinctFinal();
        return mainList;
    }

    @Override
    public LiveData<List<Pinyin>> getDetailList(@NonNull String item, Boolean isInitialFirst) {
        LiveData<List<Pinyin>> detailList;
        if (isInitialFirst)
            detailList = pinyinDao.getAllPinyinByInitial(item);
        else
            detailList = pinyinDao.getAllPinyinByFinal(item);
        return detailList;
    }

    @Override
    public Pinyin getRandomPinyin() {
        return pinyinDao.getRandomPinyin();
    }

    @Override
    public LiveData<List<Pinyin>> getAllPinyinByInitial(@NonNull String pInitial) {
        return pinyinDao.getAllPinyinByInitial(pInitial);
    }

    @Override
    public LiveData<List<Pinyin>> getAllPinyinByFinal(@NonNull String pFinal) {
        return pinyinDao.getAllPinyinByFinal(pFinal);
    }
}
