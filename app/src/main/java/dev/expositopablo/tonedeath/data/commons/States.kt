package dev.expositopablo.tonedeath.data.commons

import android.content.Context
import dev.expositopablo.tonedeath.R

sealed class State() {
    abstract class Success() : State()
    abstract class Error() : State()

    data class PinyinListSuccess(val pinyins: List<Pinyin>) : Success()
    data class PinyinSuccess(val pinyin: Pinyin) : Success()

    data class ErrorMsg(
            private val errorString: String? = null,
            private val errorRes: Int = R.string.default_error
    ) : Error() {
        fun error(context: Context) = errorString ?: context.getString(errorRes)
    }
}