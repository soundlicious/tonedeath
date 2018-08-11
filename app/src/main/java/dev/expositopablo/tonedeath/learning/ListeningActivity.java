package dev.expositopablo.tonedeath.learning;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dev.expositopablo.tonedeath.R;
import dev.expositopablo.tonedeath.androidutils.ImagesUtils;
import dev.expositopablo.tonedeath.data.commons.Constants;
import dev.expositopablo.tonedeath.data.commons.Pinyin;

public class ListeningActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PINYIN = "listeningActivity.pinyin";

    @BindView(R.id.textView_listening_pinyin)
    protected TextView pinyin;
    @BindView(R.id.button_listening_tone1)
    protected ImageButton tone1;
    @BindView(R.id.button_listening_tone2)
    protected ImageButton tone2;
    @BindView(R.id.button_listening_tone3)
    protected ImageButton tone3;
    @BindView(R.id.button_listening_tone4)
    protected ImageButton tone4;
    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    private MediaPlayer mediaPlayer;
    private ListeningViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        ButterKnife.bind(this);

        Pinyin pinyin = getIntent().getParcelableExtra(PINYIN);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListeningViewModel.class);

        if (pinyin != null)
            viewModel.setPinyin(pinyin);

        tone1.setOnClickListener(this);
        tone2.setOnClickListener(this);
        tone3.setOnClickListener(this);
        tone4.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getPinyin().observe(this, value -> pinyin.setText(value.toString()));
    }

    @Override
    public void onClick(View view) {
        String tone = null;
        switch (view.getId()) {
            case R.id.button_listening_tone1:
                tone = Constants.TONES.get(0);
                break;
            case R.id.button_listening_tone2:
                tone = Constants.TONES.get(1);
                break;
            case R.id.button_listening_tone3:
                tone = Constants.TONES.get(2);
                break;
            case R.id.button_listening_tone4:
                tone = Constants.TONES.get(3);
                break;
            default: //Nothing
        }
        String fileName = tone;
        int resID = getResources().getIdentifier(fileName, "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, resID);
        mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            enableClick();
            mediaPlayer.stop();
            mediaPlayer.release();
        });
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        disableClick();
        mediaPlayer.start();
    }

    private void disableClick() {
        setImageButtonState(tone1, false, getDrawable(R.drawable.tone_1));
        setImageButtonState(tone2, false, getDrawable(R.drawable.tone_2));
        setImageButtonState(tone3, false, getDrawable(R.drawable.tone_3));
        setImageButtonState(tone4, false, getDrawable(R.drawable.tone_4));
    }

    private void setImageButtonState(ImageButton tone, boolean isEnable, Drawable drawable) {
        tone.setEnabled(isEnable);
        if (isEnable) {
            tone.setImageDrawable(drawable);
        } else {
            tone.setImageDrawable(ImagesUtils.convertDrawableToGrayScale(drawable));
        }
    }

    private void enableClick() {
        setImageButtonState(tone1, true, getDrawable(R.drawable.tone_1));
        setImageButtonState(tone2, true, getDrawable(R.drawable.tone_2));
        setImageButtonState(tone3, true, getDrawable(R.drawable.tone_3));
        setImageButtonState(tone4, true, getDrawable(R.drawable.tone_4));
    }

    @Override
    protected void onPause() {
        super.onPause();
        destroyMediaPLayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void destroyMediaPLayer() {
        if (mediaPlayer != null) {
            enableClick();
            mediaPlayer.release();
        }
    }
}
