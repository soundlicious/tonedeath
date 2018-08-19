package dev.expositopablo.tonedeath;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import dev.expositopablo.tonedeath.data.commons.Pinyin;
import dev.expositopablo.tonedeath.data.db.PinyinDatabase;
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PinyinDaoTest {

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
    }

    @After
    public void closeDb() {
        db.clearAllTables();
        db.close();
    }

    @Test
    public void InsertAndGetPinyin() throws Exception {
        Pinyin pinyin =new Pinyin("b", "a");
        pinyinDao.insert(pinyin);
        List<Pinyin> allPinyin = LiveDataTestUtil.getValue(pinyinDao.getAllPinyin());
        assertEquals(allPinyin.get(0), pinyin);
    }


    @Test
    public void getDistinctInitials() throws Exception {
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

        List<String> allPinyin = LiveDataTestUtil.getValue(pinyinDao.getAllDistinctInitial());
        assertTrue( "returned list : " + allPinyin.size() ,allPinyin.size() == 3);
        assertEquals(pinyins.get(0).getInitial(), allPinyin.get(0));
        assertEquals(pinyins.get(9).getInitial(), allPinyin.get(1));
        assertEquals(pinyins.get(18).getInitial(), allPinyin.get(2));
    }

    @Test
    public void getDistinctFinals() throws Exception {

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

        List<String> allPinyin = LiveDataTestUtil.getValue(pinyinDao.getAllDistinctFinal());
        assertTrue( allPinyin.size() == 10);
        assertEquals(pinyins.get(0).getFinal(), allPinyin.get(0));
        assertEquals(pinyins.get(1).getFinal(), allPinyin.get(1));
        assertEquals(pinyins.get(2).getFinal(), allPinyin.get(2));
    }

    @Test
    public void getFinalListByInitial() throws Exception{
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
        pinyins.add(new Pinyin("m", "zh"));


        for (Pinyin pinyin :  pinyins){
            pinyinDao.insert(pinyin);
        }

        List<Pinyin> allPinyin = LiveDataTestUtil.getValue(pinyinDao.getAllPinyinByFinal("zh"));
        assertEquals(allPinyin.get(0), pinyins.get(pinyins.size() - 1));
        assertTrue( allPinyin.size() == 1);
    }

    @Test
    public void getInitialListByFinal() throws Exception{
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
        pinyins.add(new Pinyin("zh", "zh"));


        for (Pinyin pinyin :  pinyins){
            pinyinDao.insert(pinyin);
        }

        List<Pinyin> allPinyin = LiveDataTestUtil.getValue(pinyinDao.getAllPinyinByInitial("zh"));
        assertEquals(allPinyin.get(0), pinyins.get(pinyins.size() - 1));

    }



    @Test
    public void deleteAll() throws Exception {
        Pinyin pinyin =new Pinyin("b", "a");
        pinyinDao.insert(pinyin);
        pinyinDao.deleteAll();
        List<Pinyin> allPinyin = LiveDataTestUtil.getValue(pinyinDao.getAllPinyin());
        assertTrue(allPinyin.isEmpty());
    }
}
