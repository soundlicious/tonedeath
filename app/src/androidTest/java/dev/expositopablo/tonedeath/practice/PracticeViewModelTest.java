package dev.expositopablo.tonedeath.practice;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dev.expositopablo.tonedeath.InstantTaskExecutorRule;
import dev.expositopablo.tonedeath.data.commons.Pinyin;
import dev.expositopablo.tonedeath.data.db.PinyinDatabase;
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao;

public class PracticeViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private PinyinDao pinyinDao;
    private PinyinDatabase db;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, PinyinDatabase.class)
                .allowMainThreadQueries()
                .build();
        pinyinDao = db.pinyinDao();
        List<Pinyin> pinyins = new ArrayList<>();

        pinyins.add(new Pinyin("b", "a"));
        pinyins.add(new Pinyin("b", "o"));
        pinyins.add(new Pinyin("b", "i"));
        pinyins.add(new Pinyin("b", "u"));
        pinyins.add(new Pinyin("b", "ai"));
        pinyins.add(new Pinyin("b", "ao"));
        pinyins.add(new Pinyin("b", "an"));
        pinyins.add(new Pinyin("b", "ang"));
        pinyins.add(new Pinyin("b", "ou"));
        pinyins.add(new Pinyin("p", "a"));
        pinyins.add(new Pinyin("p", "o"));
        pinyins.add(new Pinyin("p", "i"));
        pinyins.add(new Pinyin("p", "u"));
        pinyins.add(new Pinyin("p", "ai"));
        pinyins.add(new Pinyin("p", "ao"));
        pinyins.add(new Pinyin("p", "an"));
        pinyins.add(new Pinyin("p", "ang"));
        pinyins.add(new Pinyin("p", "ou"));
        pinyins.add(new Pinyin("m", "a"));
        pinyins.add(new Pinyin("m", "o"));
        pinyins.add(new Pinyin("m", "e"));
        pinyins.add(new Pinyin("m", "i"));
        pinyins.add(new Pinyin("m", "u"));
        pinyins.add(new Pinyin("m", "ai"));
        pinyins.add(new Pinyin("m", "ao"));
        pinyins.add(new Pinyin("m", "an"));
        pinyins.add(new Pinyin("m", "ang"));
        pinyins.add(new Pinyin("m", "ou"));

        for (Pinyin pinyin :  pinyins){
            pinyinDao.insert(pinyin);
        }
    }

    @After
    public void closeDb() {
        db.clearAllTables();
        db.close();
    }

    @Test
    public void getNewPinyin() {
    }

    @Test
    public void checkAnswer() {
    }

    @Test
    public void observeScore() {
    }

    @Test
    public void getTone() {
    }

    @Test
    public void setCallback() {
    }

    @Test
    public void observeTone() {
    }

    @Test
    public void getLife() {
    }

    @Test
    public void addLife() {
    }

    @Test
    public void getScore() {
    }

    @Test
    public void getToneAnswer() {
    }

    @Test
    public void getOldTone() {
    }

    @Test
    public void getOldPinyin() {
    }
}