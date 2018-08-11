package dev.expositopablo.tonedeath.practice;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.expositopablo.tonedeath.R;
import dev.expositopablo.tonedeath.uselesswidget.UselessWidget;

public class GameOverFragment extends Fragment {

    private PracticeCallback mListener;

    @BindView(R.id.imageView_practice_gameOver_fanwujiu)
    ImageView fanwujiu;
    @BindView(R.id.imageView_practice_gameOver_xiebian2)
    ImageView xiebian;

    public GameOverFragment() {
        // Required empty public constructor
    }

    //region Lyfecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_over, container, false);
        ButterKnife.bind(this, view);
        Log.i("dev.practice", "FragmentGO : onCreateView");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        startAnimation();
        createDialog();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("dev.practice", "FragmentGO : onVieaCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("dev.practice", "FragmentGO : onAttach");

        if (context instanceof PracticeCallback) {
            mListener = (PracticeCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("dev.practice", "FragmentGO : onDetach");

        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("dev.practice", "FragmentGO : onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        getLife();
        Log.i("dev.practice", "FragmentGO : onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("dev.practice", "FragmentGO : onDestroy");

    }
    //endregion

    //region Observers
    private void getLife() {
        mListener.getViewModel().getLife().observe(getActivity(), value -> {
            if (value == 1)
                mListener.backToGame();
        });
    }
    //endregion

    //region Animations
    private void startAnimation() {
        Animation leviosa = AnimationUtils.loadAnimation(getActivity(), R.anim.levitate_up);
        leviosa.setRepeatCount(Animation.INFINITE);

        fanwujiu.startAnimation(leviosa);
        xiebian.startAnimation(leviosa);
    }
    //endregion

    //region Dialogs
    private void createDialog(){
        if (mListener.isAdLoaded()) {
            SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                    .setCustomImage(R.drawable.phoenix)
                    .setTitleText(getString(R.string.continuePlay))
                    .setContentText(getString(R.string.getExtraLife))
                    .setCancelText(getString(R.string.nothx))
                    .setConfirmText(getString(R.string.confirmWatchAd))
                    .setConfirmClickListener(sDialog -> {
                        mListener.extraLife();
                        sDialog.dismiss();
                    })
                    .showCancelButton(true)
                    .setCancelClickListener(SweetAlertDialog::dismissWithAnimation);
            dialog.setCancelable(false);
            dialog.show();
        }
    }
    //endregion

}
