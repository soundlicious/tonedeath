//package dev.expositopablo.tonedeath.views.learning;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import androidx.annotation.Nullable;
//import com.google.android.material.appbar.CollapsingToolbarLayout;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import dev.expositopablo.tonedeath.R;
//import dev.expositopablo.tonedeath.data.commons.Pinyin;
//
///**
// * A fragment representing a single InitFinal detail screen.
// * This fragment is either contained in a {@link InitFinalListActivity}
// * in two-pane mode (on tablets) or a {@link InitFinalDetailActivity}
// * on handsets.
// */
//public class InitFinalDetailFragment extends Fragment {
//
//    public static final String ARG_ISINITIAL_MAIN = "isInitialMain";
//    public static final String ARG_ITEM = "init_final_string";
//
//    private String mItem;
//    private SimpleItemRecyclerViewAdapter adapter;
//    private Callback callback;
//
//    public interface Callback {
//        void onDetailSelected(Pinyin pinyin);
//
//        InitFinalViewModel getViewModel();
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            callback = (Callback) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException("Parent Activity must implement Callback");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        callback = null;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Bundle args = getArguments();
//        if (args != null && args.containsKey(ARG_ITEM)) {
//            // Load the dummy content specified by the fragment
//            // arguments. In a real-world scenario, use a Loader
//            // to load content from a content provider.
//            mItem = args.getString(ARG_ITEM);
//            Activity activity = this.getActivity();
//            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem.toUpperCase());
//            }
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        InitFinalViewModel viewModel = callback.getViewModel();
//        View rootView = inflater.inflate(R.layout.initfinal_detail, container, false);
//
//        // Show the dummy content as text in a TextView.
//        RecyclerView recyclerView = rootView.findViewById(R.id.initfinal_detail_list);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), InitFinalListActivity.calculateNoOfColumns(getContext())));
//        adapter = new SimpleItemRecyclerViewAdapter(callback, new ArrayList<>(), !viewModel.isInitialFirst());
//        recyclerView.setAdapter(adapter);
//
//        viewModel.getDetailList(mItem).observe(this, items -> {
//            if (items != null)
//                adapter.setItems(new ArrayList<>(items));
//        });
//        return rootView;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    public static class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final Callback callback;
//        private final boolean displayInitial;
//        private List<Pinyin> mValues;
//        private final View.OnClickListener mOnClickListener;
//
//        SimpleItemRecyclerViewAdapter(Callback callback,
//                                      List<Pinyin> items,
//                                      boolean displayInitial) {
//            mValues = items;
//            this.displayInitial =  displayInitial;
//            this.callback = callback;
//            mOnClickListener = view -> {
//                String item = (String) view.getTag();
//                if (this.callback != null)
//                    this.callback.onDetailSelected(mValues.get(Integer.parseInt(item)));
//            };
//        }
//
//        @Override
//        public SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.initfinal_list_content, parent, false);
//            return new SimpleItemRecyclerViewAdapter.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
//            holder.mContentView.setText(displayInitial?mValues.get(position).getInitial():mValues.get(position).getFinal());
//            holder.itemView.setTag("" + position);
//            holder.mView.setOnClickListener(mOnClickListener);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//
//        private void setItems(ArrayList<Pinyin> items) {
//            mValues = items;
//            notifyDataSetChanged();
//        }
//
//        class ViewHolder extends RecyclerView.ViewHolder {
//            private final TextView mContentView;
//            private final View mView;
//
//            ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mContentView = view.findViewById(R.id.content);
//            }
//        }
//    }
//}
