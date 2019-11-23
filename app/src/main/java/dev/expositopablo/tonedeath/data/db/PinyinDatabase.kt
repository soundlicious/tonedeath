package dev.expositopablo.tonedeath.data.db

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

import dev.expositopablo.tonedeath.data.commons.Pinyin
import dev.expositopablo.tonedeath.data.db.dao.PinyinDao

@Database(entities = [Pinyin::class], version = 1, exportSchema = false)
abstract class PinyinDatabase : RoomDatabase() {

    abstract fun pinyinDao(): PinyinDao

    companion object {

        private var INSTANCE: PinyinDatabase? = null

        fun getDatabase(context: Context): PinyinDatabase {
            if (INSTANCE == null) {
                synchronized(PinyinDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            PinyinDatabase::class.java,
                            "pinyin_database")
                            .createFromAsset("database/pinyin_database.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback)
                            .build()
                }
            }
            return INSTANCE!!
        }

        private val roomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}
