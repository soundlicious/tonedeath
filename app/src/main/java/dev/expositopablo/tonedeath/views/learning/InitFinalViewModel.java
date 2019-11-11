//package dev.expositopablo.tonedeath.views.learning;
//
//import android.app.Application;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.annotation.NonNull;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import dev.expositopablo.tonedeath.data.commons.Pinyin;
//import dev.expositopablo.tonedeath.data.db.DataManager;
//
//public class InitFinalViewModel extends AndroidViewModel {
//
//    private final DataManager dataManager;
//    private final MutableLiveData<Boolean> isInitialFirst = new MutableLiveData<>();
//    private boolean isTablet = false;
//
//    @Inject
//    public InitFinalViewModel(@NonNull Application application, @NonNull DataManager dataManager) {
//        super(application);
//        this.dataManager = dataManager;
//        isInitialFirst.setValue(true);
//    }
//
//    public final LiveData<List<String>> getMainList(){
//        return dataManager.getMainList(isInitialFirst.getValue());
//    }
//
//    public final LiveData<List<Pinyin>> getDetailList(String item){
//        return dataManager.getDetailList(item, isInitialFirst.getValue());
//    }
//
//    public boolean isTablet() {
//        return isTablet;
//    }
//
//    public void setTablet(boolean tablet) {
//        this.isTablet = tablet;
//    }
//
//    public LiveData<List<String>> switchMainAndDetailList(){
//        isInitialFirst.setValue(!isInitialFirst.getValue());
//        return dataManager.getMainList(isInitialFirst.getValue());
//    }
//
//    public boolean isInitialFirst(){
//        return isInitialFirst.getValue();
//    }
//}
