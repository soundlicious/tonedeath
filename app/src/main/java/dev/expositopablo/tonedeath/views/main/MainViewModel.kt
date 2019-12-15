package dev.expositopablo.tonedeath.views.main

import dev.expositopablo.tonedeath.androidutils.BaseViewModel
import dev.expositopablo.tonedeath.data.commons.State
import dev.expositopablo.tonedeath.data.db.DataManager
import io.reactivex.subjects.PublishSubject

class MainViewModel(dataManager: DataManager) : BaseViewModel() {
    private val getScore = PublishSubject.create<Unit>()

    init {
        dispose(
                getScore.flatMapSingle {
                    dataManager
                            .score
                            .map<State> {
                                State.ScoreSucess(it)
                            }
                            .onErrorReturn {
                                State.ScoreSucess(0)
                            }
                }.subscribeWith(BaseDisposableObserver())
        )
    }

    fun getScore() {
        getScore.onNext(Unit)
    }
}