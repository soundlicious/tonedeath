package dev.expositopablo.tonedeath.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dev.expositopablo.tonedeath.BuildConfig;
import dev.expositopablo.tonedeath.R;
import dev.expositopablo.tonedeath.data.db.DataManager;
import dev.expositopablo.tonedeath.learning.InitFinalListActivity;
import dev.expositopablo.tonedeath.practice.PracticeActivity;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Inject
    protected DataManager dataManager;

    @BindView(R.id.textView_main_score)
    protected TextView score;
    @BindView(R.id.adView_main_banner)
    protected AdView mAdView;
    @BindView(R.id.button_main_learning)
    protected CardView learningButton;
    @BindView(R.id.button_main_practice)
    protected CardView practiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        if (!BuildConfig.DEBUG)
            Fabric.with(this, new Crashlytics());

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        learningButton.setOnClickListener(this);
        practiceButton.setOnClickListener(this);

        MobileAds.initialize(this, BuildConfig.ADMOB_TOKEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayScore();
//        loadAdBanner();
    }

    private void displayScore() {
        int scoreNumber = dataManager.getScore();
        String displayScoreText = getString(R.string.display_score);
        SpannableString span = new SpannableString(displayScoreText + " " + scoreNumber);
        span.setSpan(new StyleSpan(Typeface.BOLD), 0, displayScoreText.length(), 0);
        score.setText(span);
    }

    private void loadAdBanner() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Bundle bundleTransition = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
        switch (view.getId()) {
            case R.id.button_main_learning:
                startActivity(new Intent(this, InitFinalListActivity.class), bundleTransition);
                break;
            case R.id.button_main_practice:
                startActivity(new Intent(this, PracticeActivity.class), bundleTransition);
                break;
            default: //Nothing
        }
    }
}
