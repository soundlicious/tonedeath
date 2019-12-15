package dev.expositopablo.tonedeath.views.practice

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.expositopablo.tonedeath.androidutils.BaseViewModel
import dev.expositopablo.tonedeath.androidutils.ext.AndroidSchedulerProvider
import dev.expositopablo.tonedeath.androidutils.ext.SchedulerProvider
import dev.expositopablo.tonedeath.androidutils.ext.with
import dev.expositopablo.tonedeath.data.commons.Constants
import dev.expositopablo.tonedeath.data.commons.Pinyin
import dev.expositopablo.tonedeath.data.commons.PinyinDTO
import dev.expositopablo.tonedeath.data.commons.State
import dev.expositopablo.tonedeath.data.db.DataManager
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.PublishSubject
import kotlinx.android.parcel.Parcelize
import java.util.*

class PracticeViewModel(
        private val datamanager: DataManager
) : BaseViewModel() {
    private var currentPiyin: PinyinDTO? = null
    private var score = 0

    private val answer = PublishSubject.create<String>()
    private val scoreUpdate = PublishSubject.create<Int>()
    private val loadPinyin = PublishSubject.create<Unit>()

    private val mutableScoreState = MutableLiveData<Int>().apply { postValue(0) }
    val scoreState: LiveData<Int>
        get() = mutableScoreState

    var callback: PracticeCallback? = null

    init {
        disposeAnswer()
        diposeRandomPinyin()
        disposeScore()
        loadPinyin.onNext(Unit)
    }

    private fun disposeScore() {
        dispose(
                scoreUpdate
                        .with(AndroidSchedulerProvider())
                        .map {
                            val newScore = score + 1
                            datamanager.saveScore(newScore)
                            score = newScore
                            newScore
                        }
                        .onErrorReturn { 0 }
                        .subscribeWith(ScoreObserver())
        )
    }

    private fun diposeRandomPinyin() {
        dispose(
                loadPinyin
                        .flatMap {
                            datamanager.randomPinyin
                                    .toObservable()
                                    .map<State> {
                                        val newPinyin = PinyinDTO(
                                                it,
                                                Constants.TONES[Random().nextInt(4)],
                                                "voice${Random().nextInt(4)}"
                                        )
                                        currentPiyin = newPinyin
                                        State.PinyinSuccess(newPinyin)
                                    }
                                    .onErrorReturn {
                                        State.ErrorMsg()
                                    }
                                    .startWith(State.PinYinLoading)
                        }.subscribeWith(BaseDisposableObserver())
        )
    }

    private fun disposeAnswer() {
        dispose(
                answer.subscribe {
                    if (currentPiyin?.tone == it) {
                        scoreUpdate.onNext(score)
                        loadPinyin.onNext(Unit)
                    } else {
                        datamanager.saveScore(score)
                        callback?.gameOver(currentPiyin!!, currentPiyin!!.copy(tone = it), score)
                    }
                }
        )
    }

    fun checkAnswer(answer: String) {
        this.answer.onNext(answer)
    }

    fun removeCallback() {
        callback = null
    }

    private inner class ScoreObserver : DisposableObserver<Int>() {
        override fun onNext(t: Int) {
            score = t
            mutableScoreState.postValue(t)
        }

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            mutableScoreState.postValue(0)
        }
    }

}