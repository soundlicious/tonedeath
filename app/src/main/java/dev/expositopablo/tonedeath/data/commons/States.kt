package dev.expositopablo.tonedeath.data.commons

import android.content.Context
import dev.expositopablo.tonedeath.R
import dev.expositopablo.tonedeath.views.practice.PinyinDTO

sealed class State {
    abstract class Loading: State()
    abstract class Success : State()
    abstract class Error : State()

    object ScoreLoading: Loading()
    object PinYinLoading: Loading()

    data class PinyinListSuccess(val pinyins: List<Pinyin>) : Success()
    data class PinyinSuccess(val pinyin: PinyinDTO) : Success()
    data class ScoreSucess(val score: Int): Success()

    data class ErrorMsg(
            private val errorString: String? = null,
            private val errorRes: Int = R.string.default_error
    ) : Error() {
        fun error(context: Context) = errorString ?: context.getString(errorRes)
    }
}