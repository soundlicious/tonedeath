package dev.expositopablo.tonedeath.views.practice

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

import dev.expositopablo.tonedeath.R
import dev.expositopablo.tonedeath.data.commons.PinyinDTO
import dev.expositopablo.tonedeath.data.commons.prepareSoundMediaPlayer
import dev.expositopablo.tonedeath.views.practice.GameOverFragment.Companion.BAD
import dev.expositopablo.tonedeath.views.practice.GameOverFragment.Companion.GOOD
import dev.expositopablo.tonedeath.views.practice.GameOverFragment.Companion.SCORE
import kotlinx.android.synthetic.main.fragment_game_over.textView_practice_final_score as finalScore
import kotlinx.android.synthetic.main.fragment_game_over.imageView_practice_fanwujiu as fanwujiu
import kotlinx.android.synthetic.main.fragment_game_over.imageView_practice_xiebian as xiebian
import kotlinx.android.synthetic.main.fragment_game_over.imageButton_practice_bad as bad
import kotlinx.android.synthetic.main.fragment_game_over.imageButton_practice_good as good

data class GameOverFragmentArgs(val goodAnswer: PinyinDTO, val badAnswer: PinyinDTO, val score: Int) {
    companion object {
        fun fromBundle(bundle: Bundle?): GameOverFragmentArgs {
            return GameOverFragmentArgs(bundle?.getParcelable(GOOD)!!, bundle?.getParcelable(BAD)!!, bundle?.getInt(SCORE)!!)
        }
    }
}

fun Context.gameOverFragmentInstance(goodAnswer: PinyinDTO, badAnswer: PinyinDTO, score: Int): Fragment {
    return GameOverFragment.getInstance(goodAnswer, badAnswer, score)
}

class GameOverFragment : Fragment(R.layout.fragment_game_over) {

    private var mediaPlayer: MediaPlayer? = null
    private val args by lazy {
        GameOverFragmentArgs.fromBundle(arguments)
    }

    companion object {
        const val GOOD = "good"
        const val BAD = "bad"
        const val SCORE = "score"

        fun getInstance(goodAnswer: PinyinDTO, badAnswer: PinyinDTO, score: Int): Fragment {
            return GameOverFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GOOD, goodAnswer)
                    putParcelable(BAD, badAnswer)
                    putInt(SCORE, score)
                }
            }
        }
    }

    //region Lyfecycle
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finalScore.text = args.score.toString()
        startAnimation()
    }

    override fun onResume() {
        super.onResume()
        setButton()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    //endregion

    //region prepareUI
    private fun setButton() {
        setImage(good, args.goodAnswer)
        good.setOnClickListener { playSound(args.goodAnswer) }

        setImage(bad, args.badAnswer)
        bad.setOnClickListener { playSound(args.badAnswer) }
    }

    private fun setImage(button: ImageButton, dto: PinyinDTO) {
        context?.let {
            val resID = resources.getIdentifier(dto.tone, "drawable", it.packageName)
            button.setImageResource(resID)
        }
    }

    private fun playSound(dto: PinyinDTO) {
        mediaPlayer?.release()
        mediaPlayer = dto.prepareSoundMediaPlayer(context)
        mediaPlayer?.start()
    }
    //endregion

    //region Animations
    private fun startAnimation() {
        context?.let {
            val leviosa = AnimationUtils.loadAnimation(it, R.anim.levitate_up)
            leviosa.repeatCount = Animation.INFINITE
            fanwujiu.startAnimation(leviosa)
            xiebian.startAnimation(leviosa)
            val goUp = AnimationUtils.loadAnimation(it, R.anim.go_up)
            goUp.fillAfter = true
            finalScore.startAnimation(goUp)
        }
    }
    //endregion

}
