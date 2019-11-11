//package dev.expositopablo.tonedeath.views.practice;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.graphics.drawable.Drawable;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import android.text.SpannableString;
//import android.text.style.StyleSpan;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import dev.expositopablo.tonedeath.R;
//import dev.expositopablo.tonedeath.androidutils.ImagesUtils;
//import dev.expositopablo.tonedeath.data.commons.Constants;
//
//public class PracticeFragment extends Fragment implements View.OnClickListener {
//
//    @BindView(R.id.button_practice_tone1)
//    protected ImageButton tone1;
//    @BindView(R.id.button_practice_tone2)
//    protected ImageButton tone2;
//    @BindView(R.id.button_practice_tone3)
//    protected ImageButton tone3;
//    @BindView(R.id.button_practice_tone4)
//    protected ImageButton tone4;
//    @BindView(R.id.imageView_practice_play)
//    protected ImageView play;
//    @BindView(R.id.textView_practice_score)
//    protected TextView score;
//    @BindView(R.id.imageView_practice_coin)
//    protected ImageView coin;
//
//    private MediaPlayer mediaPlayer;
//    private PracticeCallback mListener;
//    private PracticeViewModel viewModel;
//    private ArrayList<View> animationSet;
//    private String currentTone; // Will be used once I have audio file
//
//    //region Lifecycle
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof PracticeCallback) {
//            mListener = (PracticeCallback) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement PracticeCallback");
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_practice, container, false);
//        ButterKnife.bind(this, view);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setOnClickListeners();
//
//        createAnimationSet();
//
//        setViewModel();
//
//        getCurrentTone();
//
//        getPinYin();
//
//        displayScore();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }
//
//    private void setViewModel() {
//        viewModel = mListener.getViewModel();
//        viewModel.setCallback(mListener);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    //endregion
//
//    //region Observer
//    private void getCurrentTone(){
//        viewModel.observeTone().observe(getActivity(), tone -> this.currentTone = tone);
//    }
//
//    private void displayScore() {
//        viewModel.observeScore().observe(getActivity(), value -> {
//            Context context = getActivity();
//            if (context != null) {
//                String displayScoreText = context.getString(R.string.current_score);
//                SpannableString span = new SpannableString(displayScoreText + " " + value);
//                span.setSpan(new StyleSpan(Typeface.BOLD), 0, displayScoreText.length(), 0);
//                this.score.setText(span);
//                if (value > 0)
//                    addCoinAnimation();
//            }
//        });
//    }
//
//    public void getPinYin() {
//        viewModel.getNewPinyin().observe(getActivity(), item -> {
//                    Context context = getContext();
//                    if (context != null) {
//                        playSound();
//                        newPinyinButtonAnimation();
//                    }
//                }
//        );
//    }
//    //endregion
//
//    //region Animation
//    private void createAnimationSet() {
//        animationSet = new ArrayList<>();
//        animationSet.add(tone1);
//        animationSet.add(tone2);
//        animationSet.add(tone3);
//        animationSet.add(tone4);
//    }
//
//    public void addCoinAnimation() {
//        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in);
//        coin.startAnimation(animation);
//    }
//
//    public void newPinyinButtonAnimation() {
//        int i = 0;
//        for (View animView : animationSet) {
//            Animation expandIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
//            expandIn.setStartOffset(i++ * 175);
//            animView.startAnimation(expandIn);
//        }
//    }
//    //endregion
//
//    //region Audio
//    public void playSound() {
//        disableClick();
//        String fileName = viewModel.getTone();
//        int resID = getResources().getIdentifier(fileName, "raw", getContext().getPackageName());
//        mediaPlayer = MediaPlayer.create(getContext(), resID);
//        mediaPlayer.setOnCompletionListener(mediaPlayer -> enableClick());
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        mediaPlayer.start();
//    }
//    //endregion
//
//    //region EventHandler
//    private void setOnClickListeners() {
//        play.setOnClickListener(view1 -> playSound());
//        tone1.setOnClickListener(this);
//        tone2.setOnClickListener(this);
//        tone3.setOnClickListener(this);
//        tone4.setOnClickListener(this);
//    }
//    private void enableClick() {
//        play.setImageResource(R.drawable.ic_play_arrow_black_24dp);
//        play.setEnabled(true);
//    }
//
//    private void disableClick() {
//        play.setEnabled(false);
//        Drawable disableImage = ImagesUtils.convertDrawableToGrayScale(getActivity().getDrawable(R.drawable.ic_play_arrow_black_24dp));
//        play.setImageDrawable(disableImage);
//    }
//
//    @Override
//    public void onClick(View view) {
//        String tone = null;
//        switch (view.getId()) {
//            case R.id.button_practice_tone1:
//                tone = Constants.TONES.get(0);
//                break;
//            case R.id.button_practice_tone2:
//                tone = Constants.TONES.get(1);
//                break;
//            case R.id.button_practice_tone3:
//                tone = Constants.TONES.get(2);
//                break;
//            case R.id.button_practice_tone4:
//                tone = Constants.TONES.get(3);
//                break;
//        }
//        viewModel.checkAnswer(tone);
//    }
//    //endregion
//}
