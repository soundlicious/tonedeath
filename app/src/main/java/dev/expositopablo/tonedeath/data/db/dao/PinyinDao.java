package dev.expositopablo.tonedeath.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.annotation.NonNull;

import java.util.List;

import dev.expositopablo.tonedeath.data.commons.Pinyin;
import io.reactivex.Single;

@Dao
public interface PinyinDao {

    @Query("SELECT * from pinyin_table")
    Single<List<Pinyin>> getAllPinyin();

    @Query("SELECT DISTINCT initial from pinyin_table ORDER BY final ASC")
    Single<List<String>> getAllDistinctInitial();

    @Query("SELECT DISTINCT final from pinyin_table ORDER BY final ASC")
    Single<List<String>> getAllDistinctFinal();

    @Query("SELECT * from pinyin_table WHERE initial = :pInitial ORDER BY initial")
    Single<List<Pinyin>> getAllPinyinByInitial(@NonNull String pInitial);

    @Query("SELECT * from pinyin_table WHERE final = :pFinal ORDER BY final")
    Single<List<Pinyin>> getAllPinyinByFinal(@NonNull String pFinal);

    @Query("SELECT * from pinyin_table WHERE id = abs(random()) % (SELECT max(id) FROM pinyin_table) + 1")
    Single<Pinyin> getRandomPinyin();

    @Query("SELECT COUNT(initial) FROM pinyin_table")
    Single<Integer> getRowCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pinyin pinyin);

    @Query("DELETE FROM pinyin_table")
    void deleteAll();
}
