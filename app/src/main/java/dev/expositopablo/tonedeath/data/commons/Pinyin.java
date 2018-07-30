package dev.expositopablo.tonedeath.data.commons;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pinyin_table")
public class Pinyin {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Long ID = null;

    @NonNull
    @ColumnInfo(name = "Initial")
    public final String Initial;
    @NonNull
    @ColumnInfo(name = "Final")
    public final String Final;

    public Pinyin(@NonNull String Initial, @NonNull String Final) {
        this.Initial = Initial;
        this.Final = Final;
    }

    @Override
    public String toString() {
        return Initial+Final;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Pinyin){
            Pinyin pinyin = (Pinyin) obj;
            return pinyin.toString().equals(this.toString());
        }
        return false;
    }

    public Long getID() {
        return ID;
    }

    @NonNull
    public String getInitial() {
        return Initial;
    }

    @NonNull
    public String getFinal() {
        return Final;
    }
}
