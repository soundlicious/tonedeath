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
import dev.expositopablo.tonedeath.R
import kotlinx.android.synthetic.main.activity_main.button_main_learning as learningButton
import kotlinx.android.synthetic.main.activity_main.button_main_practice as practiceButton
import kotlinx.android.synthetic.main.activity_main.textView_main_score as score

fun Context.MainActivityIntent() = Intent(this, MainActivity::class.java)

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        learningButton.setOnClickListener {

        }
        practiceButton.setOnClickListener {

        }
    }

    override fun onResume() {
        super.onResume()
        displayScore()
    }

    private fun displayScore(scoreNumber: Int = 0) {
        val displayScoreText = getString(R.string.display_score)
        val span = SpannableString("$displayScoreText $scoreNumber")
        span.setSpan(StyleSpan(Typeface.BOLD), 0, displayScoreText.length, 0)
        score.text = span
    }

    override fun onClick(view: View) {
        val bundleTransition = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        when (view.id) {
//            R.id.button_main_learning -> startActivity(new Intent(this, InitFinalListActivity.class), bundleTransition)
//            R.id.button_main_practice -> startActivity(new Intent(this, PracticeActivity.class), bundleTransition)
        }
    }

    override fun onBackPressed() {}
}
