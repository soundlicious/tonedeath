package dev.expositopablo.tonedeath.data.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dev.expositopablo.tonedeath.data.commons.Pinyin;
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao;

@Database(entities = {Pinyin.class}, version = 1)
public abstract class PinyinDatabase extends RoomDatabase{

    public abstract PinyinDao PinyinDao();

    private static PinyinDatabase INSTANCE;

    public static PinyinDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (PinyinDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PinyinDatabase.class, "pinyin_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {
        private final PinyinDao pinyinDao;

        PopulateDBAsync(PinyinDatabase db) {
            this.pinyinDao = db.PinyinDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
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
            pinyins.add(new Pinyin("f", "a"));
            pinyins.add(new Pinyin("f", "o"));
            pinyins.add(new Pinyin("f", "u"));
            pinyins.add(new Pinyin("f", "an"));
            pinyins.add(new Pinyin("f", "ang"));
            pinyins.add(new Pinyin("f", "ou"));
            pinyins.add(new Pinyin("d", "a"));
            pinyins.add(new Pinyin("d", "e"));
            pinyins.add(new Pinyin("d", "i"));
            pinyins.add(new Pinyin("d", "u"));
            pinyins.add(new Pinyin("d", "ai"));
            pinyins.add(new Pinyin("d", "ao"));
            pinyins.add(new Pinyin("d", "an"));
            pinyins.add(new Pinyin("d", "ang"));
            pinyins.add(new Pinyin("d", "ou"));
            pinyins.add(new Pinyin("d", "ong"));
            pinyins.add(new Pinyin("t", "a"));
            pinyins.add(new Pinyin("t", "e"));
            pinyins.add(new Pinyin("t", "i"));
            pinyins.add(new Pinyin("t", "u"));
            pinyins.add(new Pinyin("t", "ai"));
            pinyins.add(new Pinyin("t", "ao"));
            pinyins.add(new Pinyin("t", "an"));
            pinyins.add(new Pinyin("t", "ang"));
            pinyins.add(new Pinyin("t", "ou"));
            pinyins.add(new Pinyin("t", "ong"));
            pinyins.add(new Pinyin("n", "a"));
            pinyins.add(new Pinyin("n", "e"));
            pinyins.add(new Pinyin("n", "i"));
            pinyins.add(new Pinyin("n", "u"));
            pinyins.add(new Pinyin("n", "ü"));
            pinyins.add(new Pinyin("n", "ai"));
            pinyins.add(new Pinyin("n", "ao"));
            pinyins.add(new Pinyin("n", "an"));
            pinyins.add(new Pinyin("n", "ang"));
            pinyins.add(new Pinyin("n", "ou"));
            pinyins.add(new Pinyin("n", "ong"));
            pinyins.add(new Pinyin("l", "a"));
            pinyins.add(new Pinyin("l", "e"));
            pinyins.add(new Pinyin("l", "i"));
            pinyins.add(new Pinyin("l", "u"));
            pinyins.add(new Pinyin("l", "ü"));
            pinyins.add(new Pinyin("l", "ai"));
            pinyins.add(new Pinyin("l", "ao"));
            pinyins.add(new Pinyin("l", "an"));
            pinyins.add(new Pinyin("l", "ang"));
            pinyins.add(new Pinyin("l", "ou"));
            pinyins.add(new Pinyin("l", "ong"));
            pinyins.add(new Pinyin("g", "a"));
            pinyins.add(new Pinyin("g", "e"));
            pinyins.add(new Pinyin("g", "u"));
            pinyins.add(new Pinyin("g", "ai"));
            pinyins.add(new Pinyin("g", "ao"));
            pinyins.add(new Pinyin("g", "an"));
            pinyins.add(new Pinyin("g", "ang"));
            pinyins.add(new Pinyin("g", "ou"));
            pinyins.add(new Pinyin("g", "ong"));
            pinyins.add(new Pinyin("k", "a"));
            pinyins.add(new Pinyin("k", "e"));
            pinyins.add(new Pinyin("k", "u"));
            pinyins.add(new Pinyin("k", "ai"));
            pinyins.add(new Pinyin("k", "ao"));
            pinyins.add(new Pinyin("k", "an"));
            pinyins.add(new Pinyin("k", "ang"));
            pinyins.add(new Pinyin("k", "ou"));
            pinyins.add(new Pinyin("k", "ong"));
            pinyins.add(new Pinyin("h", "a"));
            pinyins.add(new Pinyin("h", "e"));
            pinyins.add(new Pinyin("h", "u"));
            pinyins.add(new Pinyin("h", "ai"));
            pinyins.add(new Pinyin("h", "ao"));
            pinyins.add(new Pinyin("h", "an"));
            pinyins.add(new Pinyin("h", "ang"));
            pinyins.add(new Pinyin("h", "ou"));
            pinyins.add(new Pinyin("h", "ong"));
            pinyins.add(new Pinyin("j", "i"));
            pinyins.add(new Pinyin("j", "u"));
            pinyins.add(new Pinyin("q", "i"));
            pinyins.add(new Pinyin("q", "u"));
            pinyins.add(new Pinyin("x", "i"));
            pinyins.add(new Pinyin("x", "u"));
            pinyins.add(new Pinyin("zh", "a"));
            pinyins.add(new Pinyin("zh", "e"));
            pinyins.add(new Pinyin("zh", "i"));
            pinyins.add(new Pinyin("zh", "u"));
            pinyins.add(new Pinyin("zh", "ai"));
            pinyins.add(new Pinyin("zh", "ao"));
            pinyins.add(new Pinyin("zh", "an"));
            pinyins.add(new Pinyin("zh", "ang"));
            pinyins.add(new Pinyin("zh", "ou"));
            pinyins.add(new Pinyin("zh", "ong"));
            pinyins.add(new Pinyin("ch", "a"));
            pinyins.add(new Pinyin("ch", "e"));
            pinyins.add(new Pinyin("ch", "i"));
            pinyins.add(new Pinyin("ch", "u"));
            pinyins.add(new Pinyin("ch", "ai"));
            pinyins.add(new Pinyin("ch", "ao"));
            pinyins.add(new Pinyin("ch", "an"));
            pinyins.add(new Pinyin("ch", "ang"));
            pinyins.add(new Pinyin("ch", "ou"));
            pinyins.add(new Pinyin("ch", "ong"));
            pinyins.add(new Pinyin("sh", "a"));
            pinyins.add(new Pinyin("sh", "e"));
            pinyins.add(new Pinyin("sh", "i"));
            pinyins.add(new Pinyin("sh", "u"));
            pinyins.add(new Pinyin("sh", "ai"));
            pinyins.add(new Pinyin("sh", "ao"));
            pinyins.add(new Pinyin("sh", "an"));
            pinyins.add(new Pinyin("sh", "ang"));
            pinyins.add(new Pinyin("sh", "ou"));
            pinyins.add(new Pinyin("r", "e"));
            pinyins.add(new Pinyin("r", "i"));
            pinyins.add(new Pinyin("r", "u"));
            pinyins.add(new Pinyin("r", "ao"));
            pinyins.add(new Pinyin("r", "an"));
            pinyins.add(new Pinyin("r", "ang"));
            pinyins.add(new Pinyin("r", "ou"));
            pinyins.add(new Pinyin("r", "ong"));
            pinyins.add(new Pinyin("z", "a"));
            pinyins.add(new Pinyin("z", "e"));
            pinyins.add(new Pinyin("z", "i"));
            pinyins.add(new Pinyin("z", "u"));
            pinyins.add(new Pinyin("z", "ai"));
            pinyins.add(new Pinyin("z", "ao"));
            pinyins.add(new Pinyin("z", "an"));
            pinyins.add(new Pinyin("z", "ang"));
            pinyins.add(new Pinyin("z", "ou"));
            pinyins.add(new Pinyin("z", "ong"));
            pinyins.add(new Pinyin("c", "a"));
            pinyins.add(new Pinyin("c", "e"));
            pinyins.add(new Pinyin("c", "i"));
            pinyins.add(new Pinyin("c", "u"));
            pinyins.add(new Pinyin("c", "ai"));
            pinyins.add(new Pinyin("c", "ao"));
            pinyins.add(new Pinyin("c", "an"));
            pinyins.add(new Pinyin("c", "ang"));
            pinyins.add(new Pinyin("c", "ou"));
            pinyins.add(new Pinyin("c", "ong"));
            pinyins.add(new Pinyin("s", "a"));
            pinyins.add(new Pinyin("s", "e"));
            pinyins.add(new Pinyin("s", "i"));
            pinyins.add(new Pinyin("s", "u"));
            pinyins.add(new Pinyin("s", "ai"));
            pinyins.add(new Pinyin("s", "ao"));
            pinyins.add(new Pinyin("s", "an"));
            pinyins.add(new Pinyin("s", "ang"));
            pinyins.add(new Pinyin("s", "ou"));
            pinyins.add(new Pinyin("s", "ong"));
            pinyins.add(new Pinyin("y", "a"));
            pinyins.add(new Pinyin("y", "e"));
            pinyins.add(new Pinyin("y", "i"));
            pinyins.add(new Pinyin("y", "u"));
            pinyins.add(new Pinyin("y", "ao"));
            pinyins.add(new Pinyin("y", "an"));
            pinyins.add(new Pinyin("y", "ang"));
            pinyins.add(new Pinyin("y", "ou"));
            pinyins.add(new Pinyin("y", "ong"));
            pinyins.add(new Pinyin("w", "a"));
            pinyins.add(new Pinyin("w", "o"));
            pinyins.add(new Pinyin("w", "u"));
            pinyins.add(new Pinyin("w", "ai"));
            pinyins.add(new Pinyin("w", "an"));
            pinyins.add(new Pinyin("w", "ang"));

            for (Pinyin pinyin :  pinyins){
                pinyinDao.insert(pinyin);
            }
            return null;
        }
    }
}
