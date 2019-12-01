package dev.expositopablo.tonedeath.views.main

import dev.expositopablo.tonedeath.androidutils.BaseViewModel
import dev.expositopablo.tonedeath.data.commons.State
import dev.expositopablo.tonedeath.data.db.DataManager

class MainViewModel(dataManager: DataManager) : BaseViewModel() {

    init {
        dispose(
                dataManager
                        .score
                        .map<State> {
                            State.ScoreSucess(it)
                        }
                        .onErrorReturn {
                            State.ScoreSucess(0)
                        }
                        .subscribeWith(BaseDisposableSingleObserver())
        )
    }
}