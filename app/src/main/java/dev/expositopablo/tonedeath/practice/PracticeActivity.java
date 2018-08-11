package dev.expositopablo.tonedeath.practice;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dev.expositopablo.tonedeath.R;
import dev.expositopablo.tonedeath.uselesswidget.UselessWidget;

public class PracticeActivity extends AppCompatActivity implements PracticeCallback, RewardedVideoAdListener {

    private static final String GAMEOVER = "gameOverFragment";
    private static final String PRACTICE = "PracticeFragment";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.frameLayout_practice)
    FrameLayout frameLayout;

    private RewardedVideoAd mRewardedVideoAd;
    private PracticeViewModel viewModel;

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        Log.i("dev.practice", "Activity : onCreate");

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PracticeViewModel.class);

        MobileAds.initialize(this, getString(R.string.ADMOB_TOKEN));

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        if (savedInstanceState == null) {
            changeFragment(PRACTICE);
        }
    }

    @Override
    public void onResume() {
        Log.i("dev.practice", "Activity : onResume");

        if (mRewardedVideoAd != null)
            mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("dev.practice", "Activity : onPause");
        if (mRewardedVideoAd != null)
            mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.i("dev.practice", "Activity : onDestroy");
        if (mRewardedVideoAd != null)
            mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }
    //endregion

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeFragment(String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null)
            fragment = (GAMEOVER.equals(tag))?new GameOverFragment():new PracticeFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout_practice, fragment, tag)
                .commit();
    }

    //region CallbackListeners
    @Override
    public void gameOver() {
        changeFragment(GAMEOVER);
    }

    @Override
    public void extraLife() {
        mRewardedVideoAd.show();
    }

    @Override
    public boolean isAdLoaded() {
        return mRewardedVideoAd.isLoaded();
    }

    @Override
    public void updateWidget() {
        Intent intent = new Intent(this, UselessWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(getApplicationContext())
                .getAppWidgetIds(new ComponentName(getApplication(), UselessWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        this.sendBroadcast(intent);
    }

    @Override
    public PracticeViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void backToGame() {
        changeFragment(PRACTICE);
    }
    //endregion

    //region Ad
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.ADMOB_RECOMPENSE_TOKEN),
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.i("dev.practice", "Activity : AdLoaded");
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.i("dev.practice", "Activity : AdOpem");

    }

    @Override
    public void onRewardedVideoStarted() {
        Log.i("dev.practice", "Activity : AdStarted");

    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.i("dev.practice", "Activity : AdClosed");
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Log.i("dev.practice", "Activity : AdRewarded");
        viewModel.addLife();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.i("dev.practice", "Activity : AdLeftApp");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.i("dev.practice", "Activity : adFailedToLaod");

    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.i("dev.practice", "Activity : AdVideoCompleted");
    }
    //endregion
}