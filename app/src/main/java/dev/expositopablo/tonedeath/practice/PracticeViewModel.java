package dev.expositopablo.tonedeath.practice;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dev.expositopablo.tonedeath.data.commons.Constants;
import dev.expositopablo.tonedeath.data.commons.Pinyin;
import dev.expositopablo.tonedeath.data.db.DataManager;

public class PracticeViewModel extends AndroidViewModel {

    private final DataManager datamanager;
    private MutableLiveData<String> tone = new MutableLiveData<>();
    private MutableLiveData<Integer> score = new MutableLiveData<>();
    private MutableLiveData<Integer> animOrder = new MutableLiveData<>();
    private MutableLiveData<Pinyin> pinyin = new MutableLiveData<>();
    private MutableLiveData<Integer> life = new MutableLiveData<>();
    private PracticeCallback callback;

    @Inject
    public PracticeViewModel(@NonNull Application application, @NonNull DataManager dataManager) {
        super(application);
        this.datamanager = dataManager;
        tone.setValue(Constants.TONES.get(new Random().nextInt(4)));
        score.setValue(0);
        animOrder.setValue(-1);
        life.setValue(1);

        loadPinyin();
    }

    private void loadPinyin() {
        ExecutorService service =  Executors.newSingleThreadExecutor();
        service.submit(() -> {
            Pinyin randPinyin = datamanager.getRandomPinyin();
            pinyin.postValue(randPinyin);
        });
    }

    public MutableLiveData<Pinyin> getNewPinyin() {
        return pinyin;
    }

    public void checkAnswer(String answer){
        boolean isGoodAnswer = tone.getValue().equals(answer);
        tone.setValue(Constants.TONES.get(new Random().nextInt(4)));
        loadPinyin();
        if (!isGoodAnswer) {
            life.setValue(0);
            saveScore();
            callback.gameOver();
        } else {
            addPoint();
        }
    }

    private void saveScore() {
        datamanager.saveScore(score.getValue());
        callback.updateWidget();
    }

    private void addPoint() {
        score.setValue(score.getValue() + 1);
    }

    public MutableLiveData<Integer> observeScore() {
        return score;
    }

    public String getTone() {
        return tone.getValue();
    }

    public void setCallback(PracticeCallback callback) {
        this.callback = callback;
    }

    public LiveData<String> observeTone() {
        return tone;
    }

    public MutableLiveData<Integer> getLife() {
        return life;
    }

    public void addLife() {
        life.setValue(1);
    }
}
