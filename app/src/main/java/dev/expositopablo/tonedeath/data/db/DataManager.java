package dev.expositopablo.tonedeath.data.db;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dev.expositopablo.tonedeath.data.commons.Pinyin;

@Singleton
public interface DataManager {
    void saveScore(int score);
    int getScore();

    LiveData<List<Pinyin>> getAllPinyin();
    LiveData<List<String>> getAllDistinctInitial();
    LiveData<List<String>> getAllDistinctFinal();

    LiveData<List<String>> getMainList(Boolean isInitialFirst);
    LiveData<List<Pinyin>> getDetailList(@NonNull String item, Boolean isInitialFirst);
    Pinyin getRandomPinyin();

    LiveData<List<Pinyin>> getAllPinyinByInitial(@NonNull String pInitial);
    LiveData<List<Pinyin>> getAllPinyinByFinal(@NonNull String pFinal);
}
