package dev.expositopablo.tonedeath.learning;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import dev.expositopablo.tonedeath.data.commons.Pinyin;

public class ListeningViewModel extends AndroidViewModel {

    private final MutableLiveData<Pinyin> pinyin = new MutableLiveData<>();

    @Inject
    public ListeningViewModel(@NonNull Application application) {
        super(application);
    }

    public void setPinyin(Pinyin pinyin) {
        this.pinyin.setValue(pinyin);
    }

    public MutableLiveData<Pinyin> getPinyin() {
        return pinyin;
    }
}
