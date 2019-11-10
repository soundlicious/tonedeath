package dev.expositopablo.tonedeath.practice;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.expositopablo.tonedeath.R;

public class GameOverFragment extends Fragment {

    private PracticeCallback mListener;

    @BindView(R.id.imageView_practice_fanwujiu)
    protected ImageView fanwujiu;
    @BindView(R.id.imageView_practice_xiebian)
    protected ImageView xiebian;
    @BindView(R.id.imageButton_practice_bad)
    protected ImageButton bad;
    @BindView(R.id.imageButton_practice_good)
    protected ImageButton good;
    @BindView(R.id.textView_practice_final_score)
    protected TextView finalScore;
    private MediaPlayer mediaPlayer;


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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getScore();
    }

    private void getScore() {
        mListener.getViewModel().getScore().observe(getActivity(), value -> {
            finalScore.setText(value.toString());
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof PracticeCallback) {
            mListener = (PracticeCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PracticeCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getLife();
        setButton();
    }

    private void setButton() {
        String voice = mListener.getViewModel().getOldVoice();
        String pinyin = mListener.getViewModel().getOldPinyin().toString();
        mListener.getViewModel().getOldTone().observe(getActivity(), value -> {
            setImage(good, pinyin, value, voice);
        });
        mListener.getViewModel().getToneAnswer().observe(getActivity(), value -> {
            setImage(bad, pinyin, value, voice);
        });
    }

    private void setImage(ImageButton button, String pinyin, String value, String voice) {
        Context context = getActivity();
        if (context != null) {
            int resID = getResources().getIdentifier(value, "drawable", context.getPackageName());
            button.setImageResource(resID);
            button.setOnClickListener(view -> playSound(pinyin, value, voice));

        }
    }

    private void playSound(String pinyin, String value, String voice) {
        if (mediaPlayer != null)
            mediaPlayer.release();
        String name = pinyin + "_" + value + "_" + voice;
        int resID = getResources().getIdentifier(name,  "raw", getActivity().getPackageName());
        mediaPlayer = MediaPlayer.create(getContext(), resID);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //endregion

    //region Observers
    private void getLife() {
        mListener.getViewModel().getLife().observe(getActivity(), value -> {
            if (value == 1)
                mListener.backToGame();
            else {
                startAnimation();
            }
        });
    }
    //endregion

    //region Animations
    private void startAnimation() {
        Context context = getActivity();
        if (context != null) {
            Animation leviosa = AnimationUtils.loadAnimation(context, R.anim.levitate_up);
            leviosa.setRepeatCount(Animation.INFINITE);

            fanwujiu.startAnimation(leviosa);
            xiebian.startAnimation(leviosa);
            Animation goUp = AnimationUtils.loadAnimation(context, R.anim.go_up);
            goUp.setFillAfter(true);
            finalScore.startAnimation(goUp);
        }
    }
    //endregion

}
