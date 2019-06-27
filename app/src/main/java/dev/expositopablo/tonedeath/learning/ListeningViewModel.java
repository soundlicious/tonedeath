package dev.expositopablo.tonedeath.learning;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

import javax.inject.Inject;

import dev.expositopablo.tonedeath.data.commons.Pinyin;

public class ListeningViewModel extends AndroidViewModel {

    private HashMap toneVoyelles = new HashMap() {{
        put("a", new ArrayList<>(Arrays.asList("à", "á", "ā", "ǎ")));
        put("e", new ArrayList<>(Arrays.asList("ē", "é", "ě", "è")));
        put("o", new ArrayList<>(Arrays.asList("ō", "ó", "ǒ", "ò")));
        put("u", new ArrayList<>(Arrays.asList("ū", "ú", "ǔ", "ù")));
        put("i", new ArrayList<>(Arrays.asList("ī", "í", "ǐ", "ì")));
        put("v", new ArrayList<>(Arrays.asList("ǖ", "ǘ", "ǚ", "ǜ")));
    }};


    private final MutableLiveData<String> mutableAudioStatus = new MutableLiveData<>();
    public final LiveData<String> audioStatus = mutableAudioStatus;

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

    public void getAudio(String tone) {
        int voice = new Random().nextInt(4);
        String name = formatPinyinAudioName(pinyin.getValue().toString());
        if (name != null) {
            mutableAudioStatus.postValue(name + "_" + tone + "_voice" + voice);
        }
    }

    private String formatPinyinAudioName(String name) {

        for (Object o : toneVoyelles.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            String key = (String) pair.getKey();
            ArrayList<String> values = (ArrayList<String>) pair.getValue();
            for (String value : values) {
                if (name.contains(value)) {
                    return name.replace(value, key);
                }
            }

        }
        return name;
    }
}
