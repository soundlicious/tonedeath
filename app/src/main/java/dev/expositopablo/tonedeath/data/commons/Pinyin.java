package dev.expositopablo.tonedeath.data.commons;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Entity(tableName = "pinyin_table")
public class Pinyin implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public Long ID = null;

    @NonNull
    @ColumnInfo(name = "Initial")
    public final String Initial;

    @NonNull
    @ColumnInfo(name = "Final")
    public final String Final;

    @NonNull
    @ColumnInfo(name = "ToneVoyelle")
    public final String ToneVoyelle;

    public Pinyin(@NonNull String Initial, @NonNull String Final, @NonNull String ToneVoyelle) {
        this.Initial = Initial;
        this.Final = Final;
        this.ToneVoyelle = ToneVoyelle;
    }

    @Override
    public String toString() {
        return (Initial+Final).replace("∅", "");
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

    @NonNull
    public String getToneVoyelle() {
        return ToneVoyelle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.ID);
        dest.writeString(this.Initial);
        dest.writeString(this.Final);
        dest.writeString(this.ToneVoyelle);
    }

    protected Pinyin(Parcel in) {
        this.ID = (Long) in.readValue(Long.class.getClassLoader());
        this.Initial = in.readString();
        this.Final = in.readString();
        this.ToneVoyelle = in.readString();
    }

    public static final Parcelable.Creator<Pinyin> CREATOR = new Parcelable.Creator<Pinyin>() {
        @Override
        public Pinyin createFromParcel(Parcel source) {
            return new Pinyin(source);
        }

        @Override
        public Pinyin[] newArray(int size) {
            return new Pinyin[size];
        }
    };
}
