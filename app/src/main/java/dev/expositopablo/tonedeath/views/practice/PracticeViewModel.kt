package dev.expositopablo.tonedeath.views.practice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.expositopablo.tonedeath.androidutils.BaseViewModel
import dev.expositopablo.tonedeath.data.commons.Constants
import dev.expositopablo.tonedeath.data.commons.Pinyin
import dev.expositopablo.tonedeath.data.commons.State
import dev.expositopablo.tonedeath.data.db.DataManager
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.PublishSubject
import java.util.*
import java.util.concurrent.Executors

data class PinyinDTO(
        val pinyin: Pinyin,
        val tone: String,
        val voice: String
) {
    fun getFileName(): String {
        return "${pinyin}_${tone}_$voice"
    }
}

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
    private val mutableGameOverState = MutableLiveData<Pair<PinyinDTO, String>>()
    val gameOverState: LiveData<Pair<PinyinDTO, String>>
        get() = mutableGameOverState

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
                        .reduce { acc: Int, x: Int -> acc + x }
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
                        scoreUpdate.onNext(1)
                        loadPinyin.onNext(Unit)
                    } else {
                        datamanager.saveScore(score)
                        callback?.gameOver()
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

    private inner class ScoreObserver : DisposableMaybeObserver<Int>() {
        override fun onSuccess(t: Int) {
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