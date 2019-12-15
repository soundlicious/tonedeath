package dev.expositopablo.tonedeath.views.splashscreen

import android.app.ActivityOptions
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.expositopablo.tonedeath.R
import dev.expositopablo.tonedeath.views.main.mainActivityIntent
import kotlinx.android.synthetic.main.activity_splashscreen.michigan_logo as logo

class SplashScreenActivity : AppCompatActivity() {

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        hideSystemUI()
        nextAnimation()
    }

    private fun hideSystemUI() {
        val flags = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = (flags or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    }

    private fun nextAnimation() {
        i++
        when {
            i > 6 -> rotateAndScaleUp()
            i % 2 != 0 -> scaleDown()
            else -> scaleUp()
        }

    }

    private fun scaleDown() {
        logo
                .animate()
                .scaleX(0.99f).scaleY(0.99f)
                .alpha(0.99f)
                .setDuration(1000)
                .withEndAction {
                    nextAnimation()
                }
    }

    private fun scaleUp() {
        logo
                .animate()
                .scaleX(1f).scaleY(1f)
                .alpha(1f)
                .setDuration(1000)
                .withEndAction {
                    nextAnimation()
                }
    }

    private fun rotateAndScaleUp() {
        logo.animate()
                .scaleX(30f).scaleY(30f)
                .rotationBy(360f)
                .setDuration(2000)
                .withEndAction {
                    val bundleTransition = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                    startActivity(mainActivityIntent(), bundleTransition)
                    finish()
                }
    }
}