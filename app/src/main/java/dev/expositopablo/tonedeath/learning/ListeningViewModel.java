package dev.expositopablo.tonedeath.learning;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import dev.expositopablo.tonedeath.data.commons.Pinyin;
import dev.expositopablo.tonedeath.data.db.DataManager;

public class ListeningViewModel extends AndroidViewModel {

    private MutableLiveData<Pinyin> pinyin = new MutableLiveData<>();

    @Inject
    public ListeningViewModel(@NonNull Application application) {
        super(application);
    }

    public void setPinyin(Pinyin pinyin) {
        this.pinyin.setValue(pinyin);
    }

    public Pinyin getPinyin() {
        return pinyin.getValue();
    }
}
