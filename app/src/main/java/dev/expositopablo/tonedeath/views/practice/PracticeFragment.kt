package dev.expositopablo.tonedeath.views.practice

import android.content.Context
import android.graphics.Typeface
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer

import dev.expositopablo.tonedeath.R
import dev.expositopablo.tonedeath.androidutils.ImagesUtils
import dev.expositopablo.tonedeath.data.commons.Constants
import dev.expositopablo.tonedeath.data.commons.State
import org.koin.android.viewmodel.ext.android.sharedViewModel

import kotlinx.android.synthetic.main.fragment_practice.imageView_practice_play as play
import kotlinx.android.synthetic.main.fragment_practice.button_practice_tone1 as tone1
import kotlinx.android.synthetic.main.fragment_practice.button_practice_tone2 as tone2
import kotlinx.android.synthetic.main.fragment_practice.button_practice_tone3 as tone3
import kotlinx.android.synthetic.main.fragment_practice.button_practice_tone4 as tone4
import kotlinx.android.synthetic.main.fragment_practice.imageView_practice_coin as coin
import kotlinx.android.synthetic.main.fragment_practice.textView_practice_score as score

class PracticeFragment : Fragment(R.layout.fragment_practice) {

    private val viewModel by sharedViewModel<PracticeViewModel>()

    private var mediaPlayer: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()

        viewModel.scoreState.observe(viewLifecycleOwner, Observer { value ->
            context?.let {
                val displayScoreText = it.getString(R.string.current_score)
                val span = SpannableString("$displayScoreText $value")
                span.setSpan(StyleSpan(Typeface.BOLD), 0, displayScoreText.length, 0)
                score.text = span
                if (value > 0)
                    addCoinAnimation()
            }

        })

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.PinyinSuccess -> {
                    playSound(state.pinyin)
                    newPinyinButtonAnimation()
                }
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PracticeCallback) {
            viewModel.callback = context
        } else {
            throw RuntimeException("$context must implement PracticeCallback")
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.removeCallback()
    }

    //endregion


    //region Animation

    private fun addCoinAnimation() {
        val animation = AnimationUtils.loadAnimation(activity, R.anim.slide_in)
        coin.startAnimation(animation)
    }

    private fun newPinyinButtonAnimation() {
        listOf<View>(tone1, tone2, tone3, tone4)
                .forEachIndexed { i, animView ->
                    val expandIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
                    expandIn.startOffset = (i * 175).toLong()
                    animView.startAnimation(expandIn)
                }
    }
    //endregion

    //region Audio
    private fun playSound(dto: PinyinDTO) {
        disableClick()
        context?.let {
            println(dto.getFileName())
            val resID = resources.getIdentifier(dto.getFileName(), "raw", it.packageName)
            mediaPlayer = MediaPlayer.create(it, resID).apply {
                setOnCompletionListener { enableClick() }
                setAudioStreamType(AudioManager.STREAM_MUSIC)
            }
            mediaPlayer?.start()
        }
    }
    //endregion

    //region EventHandler
    private fun setOnClickListeners() {
        play.setOnClickListener { mediaPlayer?.start() }
        tone1.setOnClickListener { viewModel.checkAnswer(Constants.TONES[0]) }
        tone2.setOnClickListener { viewModel.checkAnswer(Constants.TONES[1]) }
        tone3.setOnClickListener { viewModel.checkAnswer(Constants.TONES[2]) }
        tone4.setOnClickListener { viewModel.checkAnswer(Constants.TONES[3]) }
    }

    private fun enableClick() {
        play.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        play.isEnabled = true
    }

    private fun disableClick() {
        play.isEnabled = false
        val disableImage = ImagesUtils.convertDrawableToGrayScale(activity?.getDrawable(R.drawable.ic_play_arrow_black_24dp))
        play.setImageDrawable(disableImage)
    }
    //endregion
}
