package dev.expositopablo.tonedeath.data.commons

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.Objects

@Parcelize
@Entity(tableName = "pinyin_table")
data class Pinyin(
        val Initial: String,
        val Final: String,
        val ToneVoyelle: String
): Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    override fun toString(): String {
        return (Initial + Final)
                .replace("∅", "")
                .replace("ü", "v")
    }

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Pinyin) {
            val pinyin = other as Pinyin?
            return pinyin!!.toString() == this.toString()
        }
        return false
    }

    override fun hashCode(): Int {
        return Objects.hash(this.toString())
    }
}

@Parcelize
data class PinyinDTO(
        val pinyin: Pinyin,
        val tone: String,
        val voice: String
): Parcelable {
    fun getFileName(): String {
        return "${pinyin}_${tone}_$voice"
    }
}

fun PinyinDTO.prepareSoundMediaPlayer(context: Context?, onComplete: (() -> Unit)? = null) : MediaPlayer?{
    return context?.let {
        val resID = it.resources.getIdentifier(this.getFileName(), "raw", it.packageName)
        MediaPlayer.create(it, resID).apply {
            setOnCompletionListener { onComplete?.invoke() }
            setAudioStreamType(AudioManager.STREAM_MUSIC)
        }
    }
}
