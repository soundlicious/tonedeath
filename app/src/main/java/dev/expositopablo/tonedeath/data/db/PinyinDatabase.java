package dev.expositopablo.tonedeath.data.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import dev.expositopablo.tonedeath.data.commons.Pinyin;
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao;

@Database(entities = {Pinyin.class}, version = 1)
public abstract class PinyinDatabase extends RoomDatabase{

    public abstract PinyinDao PinyinDao();

    private static PinyinDatabase INSTANCE;

    static PinyinDatabase getDatabase(final Context context){
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

        public PopulateDBAsync(PinyinDatabase db) {
            this.pinyinDao = db.PinyinDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pinyinDao.deleteAll();
            //Insert everything here
            return null;
        }
    }
}
