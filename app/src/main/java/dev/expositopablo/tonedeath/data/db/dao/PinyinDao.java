package dev.expositopablo.tonedeath.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

import dev.expositopablo.tonedeath.data.commons.Pinyin;

@Dao
public interface PinyinDao {

    @Query("SELECT * from pinyin_table")
    LiveData<List<Pinyin>> getAllPinyin();

    @Query("SELECT DISTINCT initial from pinyin_table ORDER BY final ASC")
    LiveData<List<String>> getAllDistinctInitial();

    @Query("SELECT DISTINCT final from pinyin_table ORDER BY initial ASC")
    LiveData<List<String>> getAllDistinctFinal();

    @Query("SELECT * from pinyin_table WHERE initial = :pInitial ORDER BY initial")
    LiveData<List<Pinyin>> getAllPinyinByInitial(@NonNull String pInitial);

    @Query("SELECT * from pinyin_table WHERE final = :pFinal ORDER BY final")
    LiveData<List<Pinyin>> getAllPinyinByFinal(@NonNull String pFinal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pinyin pinyin);

    @Query("DELETE FROM pinyin_table")
    void deleteAll();
}
