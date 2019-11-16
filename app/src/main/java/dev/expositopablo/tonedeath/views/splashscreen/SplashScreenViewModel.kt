package dev.expositopablo.tonedeath.views.splashscreen

import dev.expositopablo.tonedeath.androidutils.BaseViewModel
import dev.expositopablo.tonedeath.data.db.DataManager
import io.reactivex.subjects.PublishSubject

class SplashScreenViewModel(
        private val dataManager: DataManager
) : BaseViewModel() {

    private val init = PublishSubject.create<Unit>()

//    init {
//        dispose(
//                init.flatMap {
//                    dataManager
//                            .rowCount
//                            .toObservable()
//                            .flatMap {
//                                if (it == 0) {
//                                    dataManager.initDB()
//                                }
//                            }
//                }
//        )
//    }
}