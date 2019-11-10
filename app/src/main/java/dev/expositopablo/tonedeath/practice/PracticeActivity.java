package dev.expositopablo.tonedeath.practice;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dev.expositopablo.tonedeath.BuildConfig;
import dev.expositopablo.tonedeath.R;
import dev.expositopablo.tonedeath.uselesswidget.UselessWidget;

public class PracticeActivity extends AppCompatActivity implements PracticeCallback {

    private static final String GAMEOVER = "gameOverFragment";
    private static final String PRACTICE = "PracticeFragment";

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.frameLayout_practice)
    protected FrameLayout frameLayout;

    private PracticeViewModel viewModel;

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PracticeViewModel.class);

        if (savedInstanceState == null) {
            changeFragment(PRACTICE);
        }
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
}