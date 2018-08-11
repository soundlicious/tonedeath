package dev.expositopablo.tonedeath.learning;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dev.expositopablo.tonedeath.R;
import dev.expositopablo.tonedeath.data.commons.Pinyin;

/**
 * An activity representing a list of InitFinals. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link InitFinalDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class InitFinalListActivity extends AppCompatActivity implements InitFinalDetailFragment.Callback {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private InitFinalViewModel viewModel;
    private SimpleItemRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initfinal_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InitFinalViewModel.class);
        viewModel.setTablet(getResources().getBoolean(R.bool.material_responsive_is_tablet));
        viewModel.getMainList().observe(this, items -> {
            if (items != null)
                adapter.setItems(new ArrayList<>(items));
        });

        View recyclerView = findViewById(R.id.initfinal_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab_InitFinalList_switch);
        fab.setOnClickListener(view -> viewModel.switchMainAndDetailList().observe(this, items -> {
                    if (items != null)
                        adapter.setItems(new ArrayList<>(items));
                    if (viewModel.isTablet()) {
                        Bundle arguments = new Bundle();
                        InitFinalDetailFragment fragment = new InitFinalDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.initfinal_detail_container, fragment)
                                .commit();
                    }

                }
        ));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        int columnCount = 2;
        if (!viewModel.isTablet()) {
            columnCount = calculateNoOfColumns(this);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(this, columnCount);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SimpleItemRecyclerViewAdapter(this, new ArrayList<>(), viewModel.isTablet());
        recyclerView.setAdapter(adapter);
    }

    /**
     * https://stackoverflow.com/a/44764780
     */
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int columnCount = (int) (dpWidth / scalingFactor);
        return (columnCount >= 2 ? columnCount : 2);
    }

    @Override
    public void onDetailSelected(Pinyin pinyin) {
        Intent intent = new Intent(this, ListeningActivity.class);
        intent.putExtra(ListeningActivity.PINYIN, pinyin);
        startActivity(intent);
    }

    @Override
    public InitFinalViewModel getViewModel() {
        return viewModel;
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final InitFinalListActivity mParentActivity;
        private final ArrayList<Integer> colorsList;
        private List<String> mValues;
        private final boolean mTwoPane;

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = (String) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(InitFinalDetailFragment.ARG_ITEM, item);
                    InitFinalDetailFragment fragment = new InitFinalDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.initfinal_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, InitFinalDetailActivity.class);
                    intent.putExtra(InitFinalDetailFragment.ARG_ITEM, item);
                    intent.putExtra(InitFinalDetailFragment.ARG_ISINITIAL_MAIN, mParentActivity.viewModel.isInitialFirst());
                    context.startActivity(intent);
                }
            }
        };


        SimpleItemRecyclerViewAdapter(InitFinalListActivity parent,
                                      List<String> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
            colorsList = new ArrayList<>();
            fillColorList();

        }

        private void fillColorList() {
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_red_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_pink_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_purple_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_deep_purple_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_indigo_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_blue_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_light_blue_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_cyan_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_teal_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_green_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_light_green_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_lime_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_yellow_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_amber_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_orange_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_deep_orange_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_brown_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_grey_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_blue_grey_700));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_red_200));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_pink_200));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_purple_200));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_deep_purple_200));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_indigo_200));
            colorsList.add(mParentActivity.getResources().getColor(R.color.material_color_blue_200));
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.initfinal_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mContentView.setText(mValues.get(position));

            holder.mContentView.setTextColor(colorsList.get(position));
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        private void setItems(ArrayList<String> items) {
            mValues = items;
            notifyDataSetChanged();

        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mContentView = view.findViewById(R.id.content);
            }
        }
    }
}
