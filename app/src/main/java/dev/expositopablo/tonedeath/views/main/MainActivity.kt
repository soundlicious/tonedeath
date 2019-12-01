package dev.expositopablo.tonedeath.views.main

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import dev.expositopablo.tonedeath.R
import dev.expositopablo.tonedeath.data.commons.State
import dev.expositopablo.tonedeath.views.BaseActivity
import dev.expositopablo.tonedeath.views.practice.PracticeActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import kotlinx.android.synthetic.main.activity_main.button_main_learning as learningButton
import kotlinx.android.synthetic.main.activity_main.button_main_practice as practiceButton
import kotlinx.android.synthetic.main.activity_main.textView_main_score as score

fun Context.MainActivityIntent() = Intent(this, MainActivity::class.java)

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadKoinModules(mainModule)
        learningButton.setOnClickListener {

        }
        practiceButton.setOnClickListener {
            val bundleTransition = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            startActivity(Intent(this, PracticeActivity::class.java), bundleTransition)
        }
        viewModel.state.observe(this, Observer {
            handleState(it)
        })
    }

    override fun onStop() {
        unloadKoinModules(mainModule)
        super.onStop()
    }

    private fun handleState(state: State) {
        when (state) {
            is State.ScoreSucess -> {
                displayScore(state.score)
            }
        }
    }


    private fun displayScore(scoreNumber: Int = 0) {
        score.isVisible = true
        val displayScoreText = getString(R.string.display_score)
        val span = SpannableString("$displayScoreText $scoreNumber")
        span.setSpan(StyleSpan(Typeface.BOLD), 0, displayScoreText.length, 0)
        score.text = span
    }

}
