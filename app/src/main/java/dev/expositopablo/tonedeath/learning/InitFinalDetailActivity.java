package dev.expositopablo.tonedeath.learning;

import android.app.ActivityOptions;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dev.expositopablo.tonedeath.R;
import dev.expositopablo.tonedeath.data.commons.Pinyin;

/**
 * An activity representing a single InitFinal detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link InitFinalListActivity}.
 */
public class InitFinalDetailActivity extends AppCompatActivity implements InitFinalDetailFragment.Callback {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;
    private InitFinalViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initfinal_detail);
        Toolbar toolbar;
        toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InitFinalViewModel.class);
        viewModel.setTablet(getResources().getBoolean(R.bool.material_responsive_is_tablet));

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(InitFinalDetailFragment.ARG_ITEM,
                    getIntent().getStringExtra(InitFinalDetailFragment.ARG_ITEM));
            boolean isInitialMain = getIntent().getBooleanExtra(InitFinalDetailFragment.ARG_ISINITIAL_MAIN, true);
            if(!isInitialMain)
                viewModel.switchMainAndDetailList();
            InitFinalDetailFragment fragment = new InitFinalDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.initfinal_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            navigateUpTo(new Intent(this, InitFinalListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetailSelected(Pinyin pinyin) {
        Bundle bundleTransition = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();

        Intent intent = new Intent(this, ListeningActivity.class);
        intent.putExtra(ListeningActivity.PINYIN, pinyin);
        startActivity(intent, bundleTransition);
    }

    @Override
    public InitFinalViewModel getViewModel() {
        return viewModel;
    }
}
